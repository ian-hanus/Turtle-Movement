package Model.Exceptions.Parsing;

public class CommandNotFoundException extends ParsingException {

    public CommandNotFoundException() {
        super("Command Not Found");
    }

}
