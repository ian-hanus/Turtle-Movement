package Model;

import Model.Exceptions.Parsing.ParsingException;
import frontend.TurtleState;

public class TestMain {

    public static void main(String[] args) throws ParsingException {
        Parser p = new Parser();
        p.execute("fd 50", "english");
        Result r = p.execute("fd rt 90", "english");
        TurtleState t = r.getTurtleStates().pop();
        System.out.println(t.getX() + ", " + t.getY() + "(" + t.getAngle() + ")");
        t = r.getTurtleStates().pop();
        System.out.println(t.getX() + ", " + t.getY() + "(" + t.getAngle() + ")");
    }

}
