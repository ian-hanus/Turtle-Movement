package frontend;

import javafx.beans.property.*;

public class TurtleSpriteProps {
    private final SimpleIntegerProperty id;
    private final SimpleDoubleProperty X;
    private final SimpleDoubleProperty Y;
    private final SimpleDoubleProperty angleProp;
    private final SimpleBooleanProperty downProp;
    private final SimpleBooleanProperty visibleProp;

    public TurtleSpriteProps(TurtleSprite t){
        id = new SimpleIntegerProperty(t.getID());
        X = new SimpleDoubleProperty(t.getX());
        Y = new SimpleDoubleProperty(t.getY());
        angleProp = new SimpleDoubleProperty(t.getAngle());
        downProp = new SimpleBooleanProperty(t.isPenDown());
        visibleProp = new SimpleBooleanProperty(t.isVisibility());

        System.out.println(id);
        System.out.println(X);
        System.out.println(Y);
        System.out.println(angleProp);
        System.out.println(downProp);
        System.out.println(visibleProp);

    }

    public Integer getId() {
        return id.get();
    }
    public Double getX() {
        return X.get();
    }

    public Double getY() {
        return Y.get();
    }
    public Double getAngleProp() {
        return angleProp.get();
    }
    public Boolean getDownProp() {
        return downProp.get();
    }

    public Boolean getVisibleProp() {
        return visibleProp.get();
    }
}