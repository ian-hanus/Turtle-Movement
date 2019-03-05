package Model.Exceptions.Parsing;

public class ImproperBracketsException extends ParsingException {

    public ImproperBracketsException() {
        super("Missing bracket(s)");
    }

}
