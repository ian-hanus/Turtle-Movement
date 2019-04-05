package Model.Exceptions.Parsing;

/**
 * @author Jonathan Yu
 */
public class MisalignedBracketsException extends ParsingException {

    public MisalignedBracketsException() {
        super("Brackets are not balanced");
    }

}
