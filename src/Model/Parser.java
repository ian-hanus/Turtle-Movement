<<<<<<< HEAD
//package Model;
//
//import Model.Expressions.Basic.Constant;
//import Model.Expressions.Expression;
//import backend.Result;
//import frontend.TurtleState;
//
//import java.util.*;
//
//public class Parser {
//
//    // TODO Move these to a properties file
//    private static final String OPEN_BRACKET = "[";
//    private static final String CLOSE_BRACKET = "]";
//
//    private Map<String, String> expressionClasses;
//
//    public Parser() {
//        // TODO Initialize maps (read from properties files)
//
//        expressionClasses = mapExpressionClasses();
//    }
//
//    private Map<String, String> mapExpressionClasses() {
//        // TODO Read Expression classes from properties file
//
//        return null;
//    }
//
//    public Result execute(String commands, String language) {
//        String[] translatedCommands = translate(commands, language);
//        return parse(translatedCommands);
//    }
//
//    private String[] translate(String commands, String language) {
//        return commands.split(" ");
//        // TODO Translate commands from given language to English shorthand
//    }
//
//    private Result parse(String[] commandStrings) {
//        Stack<Object> expressions = new Stack<>();
//        Deque<TurtleState> turtleChanges = new ArrayDeque<>();
//        boolean makingList = false;
//        Stack<Expression> currList = new Stack<>();
//        int numEndBrackets = 0;
//
//        for (int i = commandStrings.length - 1; i >= 0; i--) {
//            String currString = commandStrings[i];
//
//            if (currString.equals(CLOSE_BRACKET)) {
//                numEndBrackets++;
//                makingList = true;
//                continue;
//            }
//            if (currString.equals(OPEN_BRACKET)) {
//                numEndBrackets--;
//                if (numEndBrackets < 0) {
//                    // TODO Throw error for incorrect brackets
//                }
//
//                expressions.push(currList.toArray());
//
//                makingList = false;
//                continue;
//            }
//
//            try {
//                double constant = Double.parseDouble(currString);
//                expressions.push(new Constant(constant));
//            }
//            catch (NumberFormatException notConstant) {
//                if (expressionClasses.containsKey(currString)) {
//                    try {
//                        var expressionClazz = Class.forName(expressionClasses.get(currString));
//                        Expression currCommand = (Expression)expressionClazz.getDeclaredConstructor().newInstance();
//                        Class[] argTypes = currCommand.getArgumentTypes();
//                        Object[] args = new Object[argTypes.length];
//                        boolean isTurtleCommand = false;
//
//                        if (argTypes[argTypes.length - 1].equals(java.util.Deque.class)) {
//                            isTurtleCommand = true;
//                            args = new Expression[args.length - 1];
//                        }
//
//                        for (int j = args.length - 1; j >= 0; j--) {
//                            try {
//                                Object currArg = expressions.pop();
//                                /*if (!currArg.getType().equals(argTypes[j])) {
//                                    // TODO throw exception for incorrect argument type
//                                }*/
//                                args[j] = currArg;
//                            }
//                            catch (EmptyStackException incorrectArgs) {
//                                // TODO Throw exception for incorrect argument number
//                            }
//                        }
//
//                        if (isTurtleCommand) {
//                            currCommand.setArguments(args, turtleChanges);
//                        }
//                        else {
//                            currCommand.setArguments(args);
//                        }
//                        if (makingList) {
//                            currList.push(currCommand);
//                        }
//                        else {
//                            expressions.push(currCommand);
//                        }
//                    }
//                    catch (Exception reflectionException) {
//                        // TODO Do something if ClassNotFoundException or NoSuchMethodException
//                    }
//                }
//            }
//
//        }
//    }
//}
=======
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
>>>>>>> 3cb9042b6f996d5f3d443e30e9c03a3a566f3805
