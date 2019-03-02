package backend;

import java.util.Map;
import java.util.Stack;

public class Parser {

    // TODO Move these to a properties file
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSED_BRACKET = "]";

    private Map<String, String> expressionClasses;

    public Parser() {
        // TODO Initialize maps (read from properties files)

        expressionClasses = mapExpressionClasses();
    }

    private Map<String, String> mapExpressionClasses() {
        // TODO Read Expression classes from properties file

        return null;
    }

    public Result execute(String commands, String language) {
        String[] translatedCommands = translate(commands, language);
        return parse(translatedCommands);
    }

    private String[] translate(String commands, String language) {
        return commands.split(" ");
        // TODO Translate commands from given language to English shorthand
    }

    private Result parse(String[] commandStrings) {
        Stack<String> expressions = new Stack<>();
        Stack<String> expressionElements = new Stack<>();
        int numEndBrackets = 0;

        for (int i = commandStrings.length - 1; i >= 0; i++) {
            String currString = commandStrings[i];

            if (currString.equals(CLOSED_BRACKET)) {
                numEndBrackets++;
                continue;
            }
            if (currString.equals(OPEN_BRACKET)) {
                numEndBrackets--;
                if (numEndBrackets < 0) {
                    // TODO Throw error for incorrect brackets
                }
                continue;
            }

            try {
                double constant = Double.parseDouble(currString);
                
            }

        }
    }
}
