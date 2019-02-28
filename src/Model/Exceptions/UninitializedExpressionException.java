package Model.Exceptions;

public class UninitializedExpressionException extends Exception{

    public UninitializedExpressionException(String errorMessage) {
        super(errorMessage);
    }

    public UninitializedExpressionException() {
        super("Attempting to evaluate Expression without initializing arguments");
    }


}

