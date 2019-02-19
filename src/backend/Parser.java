package backend;

import java.util.List;

/**
 * Interface for the class that will parse the command string and create corresponding Expression objects.
 */
public interface Parser {

    /**
     * Parses the string representing the commands and makes a list of corresponding Expression objects.
     * @return the list of expressions
     */
    List<Expression> parse();

    /**
     * Parses an individual command string and creates the corresponding Expression object
     * @return the expression
     */
    Expression parseExpr();
}
