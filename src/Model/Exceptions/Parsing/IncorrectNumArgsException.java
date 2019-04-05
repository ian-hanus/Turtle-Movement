package Model.Exceptions.Parsing;

/**
 * @author Jonathan Yu
 */
public class IncorrectNumArgsException extends ParsingException {

    public IncorrectNumArgsException() {
        super("Incorrect number of arguments for command");
    }

}
