package Model.Exceptions;

public class AlteringExpressionException extends Exception{

    public AlteringExpressionException(String errorMessage) {
        super(errorMessage);
    }

    public AlteringExpressionException() {
        super("Attempting to alter Expression after already setting arguments");
    }


}
