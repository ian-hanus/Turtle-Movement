package Model.Exceptions.Parsing;

public class MisalignedBracketsException extends ParsingException {

    public MisalignedBracketsException() {
        super("Brackets are not balanced");
    }

}
