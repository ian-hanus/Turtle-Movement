package Model;

import Model.Exceptions.Parsing.*;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Basic.Variable;
import Model.Expressions.Controls.Make;
import Model.Expressions.Controls.To;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import Model.Expressions.Interfaces.TurtleExpression;
import frontend.TurtleState;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

// TODO What to do with exceptions that should never be thrown?
public class Parser implements Parsing {

    private final Properties expressionClasses = new Properties();
    private final Map<String, Properties> languages = new HashMap<>();
    private final Map<String, Constant> variables = new HashMap<>();

    private Properties syntax;

    private TurtleState mostRecentTurtleState;

    public Parser() {
        readLanguages();
        syntax = languages.get("syntax");
        readProperties(expressionClasses, "./src/ExpressionClasses.properties");
        mostRecentTurtleState = new TurtleState(0, 0, 90, true, true);
    }

    private void readLanguages() {
        File langDir = new File("./src/resources/languages");
        File[] langFiles = langDir.listFiles();
        if (langFiles != null) {
            for (File langF : langFiles) {
                String langName = langF.getName().toLowerCase().split("\\.")[0];
                languages.put(langName, new Properties());
                readProperties(languages.get(langName), langF.getPath());
            }
        }
    }

    private void readProperties(Properties prop, String fileName) {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            prop.load(inputStream);
        }
        catch (IOException e) {
            // TODO What to do with this exception that should never be thrown?
        }
    }

    public Result execute(String commands, String language) throws ParsingException {
        String[] translatedCommands = translate(commands, language);
        try {
            return parse(translatedCommands);
        }
        catch (ClassNotFoundException e) {
            // TODO What to do with exceptions that should never be thrown?
        }

        return null;
    }

    // TODO Refactor this
    private String[] translate(String commands, String language) throws ParsingException {
        Properties inputLanguage = languages.get(language.toLowerCase());
        String[] commandStrings = commands.toLowerCase().split(" ");
        Stack<String> bracketStack = new Stack<>();
        Pattern bracketClose = Pattern.compile(syntax.getProperty("ListEnd") + "|" + syntax.getProperty("GroupEnd"));

        for (int i = 0; i < commandStrings.length; i++) {
            String currString = commandStrings[i];

            if (bracketClose.matcher(currString).find()) {
                bracketStack.push(currString);
                continue;
            }
            if (currString.equals("[")) {
                if(!bracketStack.pop().equals("]")) {
                    throw new MisalignedBracketsException();
                }
                continue;
            }
            if (currString.equals("(")) {
                if (!bracketStack.pop().equals(")")) {
                    throw new MisalignedBracketsException();
                }
                continue;
            }

            boolean commandFound = false;
            for (String command : inputLanguage.stringPropertyNames()) {
                Pattern translation = Pattern.compile(inputLanguage.getProperty(command));
                if (translation.matcher(commandStrings[i]).find()) {
                    String[] commandOptions = languages.get("english").getProperty(command).split("\\|");
                    commandStrings[i] = commandOptions[commandOptions.length - 1].replace("\\", "");
                    commandFound = true;
                }
            }
            if (!commandFound) {
                for (String token : syntax.stringPropertyNames()) {
                    Pattern tokenSyntax = Pattern.compile(syntax.getProperty(token));
                    if (tokenSyntax.matcher(commandStrings[i]).find()) {
                        commandFound = true;
                    }
                }
            }
            if (!commandFound) {
                throw new CommandNotFoundException();
            }
        }
        return commandStrings;
    }

    // TODO Refactor this lol
    private Result parse(String[] commandStrings) throws ParsingException, ClassNotFoundException {
        Stack<Expression> superExpressions = new Stack<>();
        Deque<Object> currExpressions = new ArrayDeque<>();
        Deque<TurtleState> turtleChanges = new ArrayDeque<>();
        turtleChanges.addLast(mostRecentTurtleState);

        Pattern variableRegex = Pattern.compile(syntax.getProperty("Variable"));

        String listEnd = syntax.getProperty("ListEnd").replace("\\", "");
        String listStart = syntax.getProperty("ListStart").replace("\\", "");;
        String groupEnd = syntax.getProperty("GroupEnd").replace("\\", "");
        String groupStart = syntax.getProperty("GroupStart").replace("\\", "");

        for (int i = commandStrings.length - 1; i >= 0; i--) {
            String currString = commandStrings[i];

            if (currString.equals(listEnd) || currString.equals(groupEnd)) {
                currExpressions.push(currString);
                continue;
            }
            if (currString.equals(listStart)) {
                List<Expression> currList = new ArrayList<>();
                while (!currExpressions.getFirst().equals(listEnd)) {
                    currList.add((Expression)currExpressions.pop());
                }
                currExpressions.pop();
                currExpressions.push(currList.toArray());
                continue;
            }

            if (variableRegex.matcher(currString).find()) {
                currExpressions.push(new Variable(currString, variables));
            }

            try {
                double constant = Double.parseDouble(currString);
                currExpressions.push(new Constant(constant));
            } catch (NumberFormatException notConstant) {
                // ignore "("
                if (currString.equals(groupStart)) {
                    continue;
                }

                // TODO Implement user-defined procedures
                var expressionClass = Class.forName(expressionClasses.getProperty(currString));
                Constructor[] exprConstructors = expressionClass.getConstructors();
                Constructor exprConstructor = exprConstructors[exprConstructors.length - 1];
                Class[] exprParams = exprConstructor.getParameterTypes();
                int numParams = exprParams.length;

                Expression currCommand = null;

                // if group is complete, turn inputs into array
                if (i != 0 && commandStrings[i - 1].equals(groupStart)) {
                    List<Expression> currArgs = new ArrayList<>();
                    while (!currExpressions.getFirst().equals(groupEnd)) {
                        currArgs.add((Expression)currExpressions.pop());
                    }
                    currExpressions.pop();
                    currExpressions.push(currArgs.toArray());
                }

                // if not making list/group, push extra parameters to super expressions
                try {
                    if (!currExpressions.contains(listEnd) && !currExpressions.contains(groupEnd)) {
                        Method numExpressionsMethod = expressionClass.getDeclaredMethod("getDefaultNumExpressions");
                        int numExpressions = (Integer)numExpressionsMethod.invoke(expressionClass.getConstructor().newInstance());
                        if (currExpressions.size() > numExpressions) {
                            superExpressions.push((Expression)currExpressions.removeLast());
                        }
                    }
                }
                catch (NoSuchMethodException e) {
                    // TODO What to do with exception that should never be thrown?
                }
                catch (Exception e) {
                    // TODO What to do with reflection errors that should never be thrown?
                }
                if (!currExpressions.isEmpty()) {
                    List<Expression> currArgs = new ArrayList<>();
                    while (!currExpressions.isEmpty()) {
                        currArgs.add((Expression)currExpressions.pop());
                    }
                    currExpressions.push(currArgs);
                }

                try {
                    if (TurtleExpression.class.isAssignableFrom(expressionClass)) {
                        if (ExpressionTaker.class.isAssignableFrom(expressionClass)) {
                            currCommand = (Expression) expressionClass.getDeclaredConstructor(Deque.class, Arrays.class).newInstance(turtleChanges,(Expression[])  currExpressions.pop());
                        }
                        else {
                            currCommand = (Expression) expressionClass.getDeclaredConstructor(Deque.class).newInstance(turtleChanges);
                        }
                    }
                }

                if (expressionClass.equals(Make.class)) {
                    currExpressions.addLast(variables);
                    currExpressionTypes.addLast(Map.class);
                }
                if (expressionClass.equals(To.class)) {
                    currExpressions.addLast(expressionClasses);
                    currExpressionTypes.addLast(Properties.class);
                }
                try {
                    if (numParams == 0) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor().newInstance();
                    } else if (numParams == 1) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(currExpressionTypes.pop()).newInstance(currExpressions.pop());
                    } else if (numParams == 2) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(currExpressionTypes.pop(), currExpressionTypes.pop()).newInstance(currExpressions.pop(), currExpressions.pop());
                    } else {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(currExpressionTypes.pop(), currExpressionTypes.pop(), currExpressionTypes.pop()).newInstance(currExpressions.pop(), currExpressions.pop(), currExpressions.pop());
                    }
                } catch (EmptyStackException e) {
                    throw new IncorrectNumArgsException();
                } catch (NoSuchMethodException e) {
                    throw new IncorrectArgTypeException();
                } catch (Exception e) {
                    // TODO What to do with reflection errors that should never be thrown?
                    e.printStackTrace();
                }
                if (makingList) {
                    currList.push(currCommand);
                } else {
                    currExpressions.push(currCommand);
                    currExpressionTypes.push(Expression.class);
                }
            }
        }

        while (!currExpressions.isEmpty()) {
            // TODO I don't think this is right
            superExpressions.push((Expression)currExpressions.pop());
        }

        double returnValue = 0;
        // TODO Delete this later
        while (!superExpressions.empty()) {
            returnValue = superExpressions.pop().evaluate();
        }

        mostRecentTurtleState = turtleChanges.getLast();
        turtleChanges.pop();

        return new Result(returnValue, turtleChanges);
    }
}
