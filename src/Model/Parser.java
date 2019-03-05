package Model;

import Model.Exceptions.Parsing.*;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Controls.Make;
import Model.Expressions.Controls.To;
import Model.Expressions.Expression;
import frontend.TurtleState;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.regex.Pattern;

// TODO What to do with exceptions that should never be thrown?
public class Parser implements Parsing {

    private final Properties expressionClasses = new Properties();
    private final  Map<String, Properties> languages = new HashMap<>();

    private Map<String, Constant> variables;

    public Parser() {
        readLanguages();
        readProperties(expressionClasses, "src/Model/ExpressionClasses.properties");
        variables = new HashMap<>();
    }

    private void readLanguages() {
        File langDir = new File("src/resources.languages");
        File[] langFiles = langDir.listFiles();
        if (langFiles != null) {
            for (File langF : langFiles) {
                String langName = langF.getName().toLowerCase().split(".")[0];
                languages.put(langName, new Properties());
                readProperties(languages.get(langName), langF.getName());
            }
        }
    }

    private void readProperties(Properties prop, String fileName) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException();
            }
        }
        catch (IOException e) {
            // TODO What to do with this exception that should never be thrown?
            e.printStackTrace();
        }
    }

    public Result execute(String commands, String language) throws ParsingException {
        String[] translatedCommands = translate(commands, language);
        try {
            return parse(translatedCommands);
        } catch (ClassNotFoundException | UninitializedExpressionException e) {
            // TODO What to do with these exceptions that are never thrown?
            e.printStackTrace();
        }

        return null;
    }

    private String[] translate(String commands, String language) throws CommandNotFoundException {
        // TODO Translate commands from given language to English shorthand (lower-case)
        Properties inputLanguage = languages.get(language);
        String[] commandStrings = commands.toLowerCase().split(" ");
        for (int i = 0; i < commandStrings.length; i++) {
            boolean commandFound = false;
            for (String name : inputLanguage.stringPropertyNames()) {
                Pattern regex = Pattern.compile(name);
                if (regex.matcher(commandStrings[i]).find()) {
                    String[] commandOptions = languages.get("english").getProperty(name).split("|");
                    commandStrings[i] = commandOptions[commandOptions.length - 1].replace("\\", "");
                    commandFound = true;
                }
            }
            if (!commandFound) {
                for (String name : languages.get("syntax").stringPropertyNames()) {
                    Pattern regex = Pattern.compile(name);
                    if (regex.matcher(commandStrings[i]).find()) {
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
    private Result parse(String[] commandStrings) throws ParsingException, ClassNotFoundException, UninitializedExpressionException {
        Stack<Expression> superExpressions = new Stack<>();
        Deque<Object> currExpressions = new ArrayDeque<>();
        Deque<Class> currExpressionTypes = new ArrayDeque<>();

        Properties syntax = languages.get("syntax");
        Pattern variableRegex = Pattern.compile(syntax.getProperty("Variable"));

        int numEndBrackets = 0;
        boolean makingList = false;
        Deque<Expression> currList = new ArrayDeque<>();

        Deque<TurtleState> turtleChanges = new ArrayDeque<>();

        for (int i = commandStrings.length - 1; i >= 0; i--) {
            String currString = commandStrings[i];

            if (currString.equals(syntax.getProperty("ListEnd").replace("\\", ""))) {
                numEndBrackets++;
                makingList = true;
                continue;
            }
            if (currString.equals(syntax.getProperty("ListStart").replace("\\", ""))) {
                numEndBrackets--;
                if (numEndBrackets < 0) {
                    throw new ImproperBracketsException();
                }
                makingList = false;
                currExpressions.addFirst(currList.toArray());
                continue;
            }

            if (variableRegex.matcher(currString).find()) {
                if (!variables.containsKey(currString)) {
                    variables.put(currString, new Constant(0));
                }
                currExpressions.addFirst(variables.get(currString));
            }

            try {
                double constant = Double.parseDouble(currString);
                currExpressions.push(new Constant(constant));
                currExpressions.push(Constant.class);
            } catch (NumberFormatException notConstant) {
                // TODO Implement user-defined procedures
                var expressionClass = Class.forName(expressionClasses.getProperty(currString));
                Constructor[] exprConstructors = expressionClass.getConstructors();
                Constructor exprConstructor = exprConstructors[exprConstructors.length - 1];
                Class[] exprParams = exprConstructor.getParameterTypes();
                int numParams = exprParams.length;

                Expression currCommand = null;

                // TODO Uncomment this after Sachal implements getNumCommandArgs() method
                /*
                try {
                    Method getNumCommandArgs = expressionClass.getDeclaredMethod("getNumCommandArgs");
                    int numCommandArgs = (Integer)getNumCommandArgs.invoke(expressionClass.getConstructor().newInstance());
                    if (currExpressions.size() > numCommandArgs) {
                        superExpressions.push((Expression) currExpressions.getLast());
                        currExpressionTypes.getLast();
                    }
                }
                catch(ClassCastException e) {
                    throw new IncorrectArgTypeException();
                }
                catch(Exception e) {
                    // TODO What to do with reflection errors that should never be thrown?
                }
                */

                if (exprParams[numParams - 1].equals(Deque.class)) {
                    currExpressions.addLast(turtleChanges);
                    currExpressionTypes.addLast(Deque.class);
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
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(currExpressionTypes.getFirst()).newInstance(currExpressions.getFirst());
                    } else if (numParams == 2) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(currExpressionTypes.getFirst(), currExpressionTypes.getFirst()).newInstance(currExpressions.getFirst(), currExpressions.getFirst());
                    } else {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(currExpressionTypes.getFirst(), currExpressionTypes.getFirst(), currExpressionTypes.getFirst()).newInstance(currExpressions.getFirst(), currExpressions.getFirst(), currExpressions.getFirst());
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
                    currList.addFirst(currCommand);
                } else {
                    currExpressions.addFirst(currCommand);
                }
            }
        }

        double returnValue = 0;
        while (!superExpressions.empty()) {
            returnValue = superExpressions.pop().evaluate();
        }

        return new Result(returnValue, turtleChanges);
    }
}
