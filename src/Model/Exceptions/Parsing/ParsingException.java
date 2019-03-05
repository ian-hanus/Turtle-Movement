package Model.Exceptions.Parsing;

public abstract class ParsingException extends Exception {

    public ParsingException(String errorMessage) {
        super(errorMessage);
    }

}
