package Model;

import Model.Exceptions.Parsing.CommandNotFoundException;
import Model.Exceptions.Parsing.MisalignedBracketsException;
import Model.Exceptions.Parsing.ParsingException;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Basic.Procedure;
import Model.Expressions.Basic.ProcedureFactory;
import Model.Expressions.Basic.Variable;
import Model.Expressions.Controls.Make;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import Model.Expressions.Interfaces.TurtleExpression;
import frontend.TurtleState;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

// TODO What to do with exceptions that should never be thrown?

/**
 * @author Jonathan Yu
 */
public class Parser implements Parsing {

    private final Properties expressionClasses = new Properties();
    private final Map<String, Properties> languages = new HashMap<>();
    private final Map<String, Constant> variables = new HashMap<>();
    private final Map<String, ProcedureFactory> procedures = new HashMap<>();

    private Properties syntax;

    private TurtleState mostRecentTurtleState;

    public Parser() {
        readLanguages();
        syntax = languages.get("syntax");
        readProperties(expressionClasses, "./src/ExpressionClasses.properties");
        mostRecentTurtleState = new TurtleState();
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
        } catch (IOException e) {
            // TODO What to do with this exception that should never be thrown?
        }
    }

    /**
     * Translates and parses the string of commands. Then, runs each command and returns the result of the last one.
     * @param commands the string representing all of the entered
     * @param language the language of the entered commands
     * @return the return value of the final command
     * @throws ParsingException error in how the commands were entered, caught by the parser
     */
    public Result execute(String commands, String language) throws ParsingException {
        String[] translatedCommands = translate(commands, language);
        return parse(translatedCommands);
    }

    // TODO Refactor this
    private String[] translate(String commands, String language) throws ParsingException {
        Properties inputLanguage = languages.get(language.toLowerCase());
        String[] commandStrings = commands.toLowerCase().split(" ");
        Stack<String> bracketStack = new Stack<>();

        for (int i = commandStrings.length - 1; i >= 0; i--) {
            String currString = commandStrings[i];

            if (Pattern.compile(syntax.getProperty("ListEnd")).matcher(currString).find() || Pattern.compile(syntax.getProperty("GroupEnd")).matcher(currString).find()) {
                bracketStack.push(currString);
                continue;
            }
            if (currString.equals("[")) {
                if (!bracketStack.pop().equals("]")) {
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
    private Result parse(String[] commandStrings) throws ParsingException {
        Stack<Expression> superExpressions = new Stack<>();
        Deque<Object> currExpressions = new ArrayDeque<>();
        Deque<TurtleState> turtleChanges = new ArrayDeque<>();
        turtleChanges.addLast(mostRecentTurtleState);

        Pattern variableRegex = Pattern.compile(syntax.getProperty("Variable"));

        String listEnd = syntax.getProperty("ListEnd").replace("\\", "");
        String listStart = syntax.getProperty("ListStart").replace("\\", "");
        String groupEnd = syntax.getProperty("GroupEnd").replace("\\", "");
        String groupStart = syntax.getProperty("GroupStart").replace("\\", "");

        for (int i = commandStrings.length - 1; i >= 0; i--) {
            String currString = commandStrings[i];

            if (currString.equals(listEnd) || currString.equals(groupEnd)) {
                currExpressions.push(currString);
            } else if (currString.equals(listStart)) {
                List<Expression> currList = new ArrayList<>();
                while (!currExpressions.getFirst().equals(listEnd)) {
                    currList.add((Expression) currExpressions.pop());
                }
                currExpressions.pop();
                currExpressions.push(currList.toArray());
            } else if (variableRegex.matcher(currString).find()) {
                if (i != commandStrings.length - 1 && commandStrings[i + 1].equals(listStart)) {
                    currExpressions.push(currString);
                }
                else if (i != 0 && commandStrings[i-1].equals("set")) {
                    currExpressions.push(new Make(currString, variables, (Expression)currExpressions.pop()));
                }
                else {
                    currExpressions.push(new Variable(currString, variables));
                }

            } else if (currString.equals("set")) {
                // TODO do nothing
            } else if (Pattern.compile(syntax.getProperty("Constant")).matcher(currString).find()) {
                double constant = Double.parseDouble(currString);
                currExpressions.push(new Constant(constant));
            } else {
                try {
                    // ignore "("
                    if (currString.equals(groupStart)) {
                        continue;
                    }

                    if (expressionClasses.getProperty(currString) == null) {
                        if (procedures.keySet().contains(currString)) {
                            List<Expression> currArgs = new ArrayList<>();
                            while (!currExpressions.isEmpty()) {
                                currArgs.add((Expression) currExpressions.pop());
                            }
                            Expression[] inputs = new Expression[currArgs.size()];
                            for (int j = 0; j < currArgs.size(); j++) {
                                inputs[j] = currArgs.get(j);
                            }
                            Procedure proc = procedures.get(currString).acceptParameters(inputs);
                            currExpressions.push(proc);
                        } else {
                            throw new CommandNotFoundException();
                        }
                        continue;
                    }

                    var expressionClass = Class.forName(expressionClasses.getProperty(currString));
                    Expression currCommand;
                    Expression[] inputs = null;

                    // if not making list/group, push extra parameters to super expressions
                    /*if (!currExpressions.contains(listEnd) && !currExpressions.contains(groupEnd)) {
                        Method numExpressionsMethod = expressionClass.getDeclaredMethod("getDefaultNumExpressions");
                        int numExpressions = (Integer) numExpressionsMethod.invoke(expressionClass.getConstructor().newInstance());
                        if (currExpressions.size() > numExpressions) {
                            superExpressions.push((Expression) currExpressions.removeLast());
                        }
                    } */

                    // if group is complete, turn inputs into array
                    if (i != 0 && commandStrings[i - 1].equals(groupStart)) {
                        List<Expression> currArgs = new ArrayList<>();
                        while (!currExpressions.getFirst().equals(groupEnd)) {
                            currArgs.add((Expression) currExpressions.pop());
                        }
                        currExpressions.pop();
                        inputs = new Expression[currArgs.size()];
                        for (int j = 0; j < currArgs.size(); j++) {
                            inputs[j] = currArgs.get(j);
                        }
                    }

                    if (!currExpressions.isEmpty()) {
                        List<Expression> currArgs = new ArrayList<>();
                        Stack<Expression[]> lists = new Stack<>();
                        while (!currExpressions.isEmpty()) {
                            Object curr = currExpressions.pop();
                            if (curr instanceof Expression[]) {
                                lists.push((Expression[]) curr);
                            } else {
                                currArgs.add((Expression) curr);
                            }
                        }

                        while (!lists.isEmpty()) {
                            currExpressions.addLast(lists.pop());
                        }

                        inputs = new Expression[currArgs.size()];
                        for (int j = 0; j < currArgs.size(); j++) {
                            inputs[j] = currArgs.get(j);
                        }
                    }

                    if (TurtleExpression.class.isAssignableFrom(expressionClass)) {
                        if (ExpressionTaker.class.isAssignableFrom(expressionClass)) {
                            currCommand = (Expression) expressionClass.getDeclaredConstructor(Deque.class, Expression[].class).newInstance(turtleChanges, inputs);
                        } else {
                            currCommand = (Expression) expressionClass.getDeclaredConstructor(Deque.class).newInstance(turtleChanges);
                        }
                    } else if (Pattern.compile("dotimes|for|if").matcher(currString).find()) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(String.class, Map.class, Expression[].class).newInstance((String) currExpressions.pop(), (Map) currExpressions.pop(), (Expression[]) currExpressions.pop());
                    } else if (currString.equals("ifelse")) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(Expression[].class, Expression[].class, Expression[].class).newInstance((Expression[]) currExpressions.pop(), (Expression[]) currExpressions.pop(), (Expression[]) currExpressions.pop());
                    } else if (currString.equals("repeat")) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(String.class, Expression[].class).newInstance((String) currExpressions.pop(), (Expression[]) currExpressions.pop());
                    } else if (currString.equals("To")) {
                        String name = (String) currExpressions.pop();
                        Expression[] vars = (Expression[]) currExpressions.pop();
                        String[] varNames = new String[vars.length];
                        for (int j = 0; j < vars.length; j++) {
                            Variable currVar = (Variable) vars[j];
                            varNames[j] = currVar.getName();
                        }
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(Map.class, Map.class, String.class, String[].class, Expression[].class).newInstance(variables, procedures, name, varNames, inputs);
                    } else {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(Expression[].class).newInstance(inputs);
                    }
                    currExpressions.push(currCommand);
                } catch (ParsingException e) {
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO split up into correct exceptions
                }
            }
        }

        while (!currExpressions.isEmpty()) {
            superExpressions.push((Expression) currExpressions.pop());
        }

        double returnValue = 0;
        // TODO Delete this later
        while (!superExpressions.empty()) {
            returnValue = superExpressions.pop().evaluate();
        }

        mostRecentTurtleState = turtleChanges.getLast();
        turtleChanges.pop();

        Map<String, String> varMap = new HashMap<>();
        for (Map.Entry<String, Constant> varEntry : variables.entrySet()) {
            varMap.put(varEntry.getKey(), Double.toString(varEntry.getValue().evaluate()));
        }

        Map<String, String[]> procMap = new HashMap<>();
        for (Map.Entry<String, ProcedureFactory> procEntry : procedures.entrySet()) {
            procMap.put(procEntry.getKey(), procEntry.getValue().getVariables());
        }

        return new Result(returnValue, turtleChanges, varMap, procMap);
    }

}
