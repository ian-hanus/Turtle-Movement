package Model.Exceptions.Parsing;

/**
 * @author Jonathan Yu
 */
public abstract class ParsingException extends Exception {

    public ParsingException(String errorMessage) {
        super(errorMessage);
    }

}
