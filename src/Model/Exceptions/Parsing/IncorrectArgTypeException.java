package Model.Exceptions.Parsing;

public class IncorrectArgTypeException extends ParsingException {

    public IncorrectArgTypeException() {
        super("Incorrect type for command argument");
    }

}
