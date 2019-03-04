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
