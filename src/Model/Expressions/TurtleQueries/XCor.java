package Model.Expressions.TurtleQueries;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;
import java.util.Deque;
import frontend.TurtleState;

public class XCor extends Expression {

    private Deque<TurtleState> queue;

    public XCor(Deque<TurtleState> queue) throws AlteringExpressionException
    {
        setArguments(queue);
    }

    public void setArguments(Deque<TurtleState> queue) throws AlteringExpressionException{
        finalizeStates();
        this.queue = queue;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        TurtleState copy = new TurtleState(queue.getLast());
        return copy.getX();
    }

    @Override
    public Class[] getArgumentTypes() {
        return new Class[]{java.util.Deque.class};
    }
}
