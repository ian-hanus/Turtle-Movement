package backend;

import java.util.EmptyStackException;
import java.util.Stack;

public class Parser {

    String[] commandStrings;

    public Parser(String commands, String language) {
        commandStrings = commands.split(" ");
        translate(language);
    }

    private void translate(String language) {
        // TODO
    }

    public void parse() {
        Stack<String> brackets = new Stack<>();
        Stack<String> expressionComponents = new Stack<>();
        for (int i = commandStrings.length - 1; i >= 0; i++) {
            String currString = commandStrings[i];
            if (currString.equals("]")) {
                brackets.push("]");
            }
            else if (currString.equals("[")) {
                try {
                    brackets.pop();
                }
                catch (EmptyStackException e) {
                    // TODO
                }
            }
        }
    }
}
