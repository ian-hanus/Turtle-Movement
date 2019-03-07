package Model;

import Model.Exceptions.Parsing.ParsingException;
import frontend.TurtleState;

public class TestMain {

    public static void main(String[] args) throws ParsingException {
        Parser p = new Parser();
        Result r = p.execute("fd 50", "english");
        TurtleState t = r.getTurtleStates().pop();
        System.out.println(t.getX() + ", " + t.getY());
    }

}
