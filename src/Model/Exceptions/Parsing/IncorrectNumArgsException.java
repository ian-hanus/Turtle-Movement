package Model.Exceptions.Parsing;

public class IncorrectNumArgsException extends ParsingException {

    public IncorrectNumArgsException() {
        super("Incorrect number of arguments for command");
    }

}
