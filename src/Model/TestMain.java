package Model;

import Model.Exceptions.Parsing.ParsingException;

public class TestMain {

    public static void main(String[] args) throws ParsingException {
        Parser p = new Parser();
        Result r = p.execute("fd 50", "english");
        System.out.println(r.getTurtleStates().getFirst().getX());
        System.out.println(r.getReturnValue());
    }

}
