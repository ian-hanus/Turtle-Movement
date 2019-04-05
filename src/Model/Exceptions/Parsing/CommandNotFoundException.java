package Model.Exceptions.Parsing;

/**
 * @author Jonathan Yu
 */
public class CommandNotFoundException extends ParsingException {

    public CommandNotFoundException() {
        super("Command Not Found");
    }

}
