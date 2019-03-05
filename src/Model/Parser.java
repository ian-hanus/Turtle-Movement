package Model;

import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Controls.Make;
import Model.Expressions.Controls.To;
import Model.Expressions.Expression;
import frontend.TurtleState;
import frontend.Variable;

import java.lang.reflect.Constructor;
import java.util.*;

public class Parser {

    // TODO Move these to a properties file
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";

    private Map<String, String> expressionClasses;
    private Map<String, Constant> variables;

    public Parser() {
        // TODO Initialize maps (read from properties files)

        expressionClasses = mapExpressionClasses();
        variables = new HashMap<>();
    }

    private Map<String, String> mapExpressionClasses() {
        // TODO Read Expression classes from properties file

        return null;
    }

    public Result execute(String commands, String language) throws ClassNotFoundException, UninitializedExpressionException {
        String[] translatedCommands = translate(commands, language);
        return parse(translatedCommands);
    }

    private String[] translate(String commands, String language) {
        return commands.split(" ");
        // TODO Translate commands from given language to English shorthand
    }

    private Result parse(String[] commandStrings) throws ClassNotFoundException, UninitializedExpressionException {
        Stack<Expression> superExpressions = new Stack<>();
        Deque<Object> currExpressions = new ArrayDeque<>();
        Deque<Class> expressionTypes = new ArrayDeque<>();
        Deque<TurtleState> turtleChanges = new ArrayDeque<>();
        int numEndBrackets = 0;
        boolean makingList = false;
        Deque<Expression> currList = new ArrayDeque<>();

        for (int i = commandStrings.length - 1; i >= 0; i--) {
            String currString = commandStrings[i];

            if (currString.equals(CLOSE_BRACKET)) {
                numEndBrackets++;
                makingList = true;
                continue;
            }
            if (currString.equals(OPEN_BRACKET)) {
                numEndBrackets--;
                if (numEndBrackets < 0) {
                    // TODO Throw error for incorrect brackets
                }
                makingList = false;
                currExpressions.addFirst(currList.toArray());
                continue;
            }

            if (currString.substring(0,1).equals(":")) {
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
                var expressionClass = Class.forName(expressionClasses.get(currString));
                Constructor[] exprConstructors = expressionClass.getConstructors();
                Constructor exprConstructor = exprConstructors[exprConstructors.length - 1];
                Class[] exprParams = exprConstructor.getParameterTypes();
                int numParams = exprParams.length;
                Expression currCommand = null;

                // TODO Find a better way to determine when to push to superExpressions
                try {
                    if (exprParams[numParams - 1].equals(Deque.class)) {
                        if (numParams > currExpressions.size() + 1) {
                            superExpressions.push((Expression) currExpressions.getLast());
                            expressionTypes.getLast();
                        }
                        currExpressions.addLast(turtleChanges);
                        expressionTypes.addLast(Deque.class);
                    } else {
                        if (numParams > currExpressions.size()) {
                            superExpressions.push((Expression) currExpressions.getLast());
                            expressionTypes.getLast();
                        }
                    }
                } catch (ClassCastException e) {
                    // TODO Throw exception for incorrect number of arguments
                }

                if (expressionClass.equals(Make.class)) {
                    currExpressions.addLast(variables);
                    expressionTypes.addLast(Map.class);
                }
                if (expressionClass.equals(To.class)) {
                    currExpressions.addLast(expressionClasses);
                    expressionTypes.addLast(Map.class);
                }

                try {
                    if (numParams == 0) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor().newInstance();
                    } else if (numParams == 1) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(expressionTypes.getFirst()).newInstance(currExpressions.getFirst());
                    } else if (numParams == 2) {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(expressionTypes.getFirst(), expressionTypes.getFirst()).newInstance(currExpressions.getFirst(), currExpressions.getFirst());
                    } else {
                        currCommand = (Expression) expressionClass.getDeclaredConstructor(expressionTypes.getFirst(), expressionTypes.getFirst(), expressionTypes.getFirst()).newInstance(currExpressions.getFirst(), currExpressions.getFirst(), currExpressions.getFirst());
                    }
                } catch (EmptyStackException e) {
                    // TODO Throw exception for incorrect number of args
                } catch (NoSuchMethodException e) {
                    // TODO Throw exception for incorrect type of args
                } catch (Exception e) {
                    // TODO What to do with other java exceptions?
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
