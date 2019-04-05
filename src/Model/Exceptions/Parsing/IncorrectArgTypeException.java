package Model.Exceptions.Parsing;

/**
 * @author Jonathan Yu
 */
public class IncorrectArgTypeException extends ParsingException {

    public IncorrectArgTypeException() {
        super("Incorrect type for command argument");
    }

}
