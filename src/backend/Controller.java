package backend;

import java.util.List;
import java.util.Queue;

/**
 * Interface for the main class of the backend. The frontend will pass the commands entered into the terminal as strings
 * to the class that implements this interface. The class will then use the Parser class to parse the strings and create
 * equivalent Expression class objects. Then, the class will evaluate each of those expressions and allow the frontend
 * to access the results of the commands.
 */
public interface Controller {

    /**
     * Evaluates each of the expressions that represent the parsed commands
     *
     * @param commands the string representing the commands passed from the frontend
     * @return the results of the commands
     */
    Queue<Object> runCommands(String commands);

    /**
     * Uses the Parser class to parse the string representing the current commands. Will be private.
     *
     * @param commands the string representing the commands passed from the frontend
     * @return the list of Expression objects that correspond to the commands
     */
    List<Expression> parseCommands(String commands);
}
