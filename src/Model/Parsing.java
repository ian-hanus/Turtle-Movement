package Model;

import Model.Exceptions.Parsing.ParsingException;

public interface Parsing {

    Result execute(String commands, String language) throws ParsingException;

}
