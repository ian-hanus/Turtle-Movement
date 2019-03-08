package frontend;

import javafx.beans.property.*;

public class TurtleSpriteProps {
    private final SimpleIntegerProperty id;
    private final SimpleDoubleProperty X;
    private final SimpleDoubleProperty Y;
    private final SimpleDoubleProperty angle;
    private final SimpleBooleanProperty down;
    private final SimpleBooleanProperty visible;

    public TurtleSpriteProps(TurtleSprite t){
        id = new SimpleIntegerProperty(t.getID());
        X = new SimpleDoubleProperty(t.getX());
        Y = new SimpleDoubleProperty(t.getY());
        angle = new SimpleDoubleProperty(t.getAngle());
        down = new SimpleBooleanProperty(t.isPenDown());
        visible = new SimpleBooleanProperty(t.isVisibility());

    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }


    public SimpleDoubleProperty xProperty() {
        return X;
    }

    public SimpleDoubleProperty yProperty() {
        return Y;
    }

    public SimpleDoubleProperty angleProperty() {
        return angle;
    }
    public SimpleBooleanProperty downProperty() {
        return down;
    }
    public SimpleBooleanProperty visibleProperty() {
        return visible;
    }

}
