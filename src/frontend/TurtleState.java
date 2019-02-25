package frontend;

import backend.Turtle;

public class TurtleState {
    private int x;
    private int y;
    private double angle;
    private boolean visible;
    private boolean down;



    public TurtleState(int xpos, int ypos, double orientation, boolean isVisible, boolean isDown){
        x = xpos;
        y = ypos;
        angle = orientation;
        visible = isVisible;
        down = isDown;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public double getAngle() {
        return angle;
    }
    public boolean isVisible() {
        return visible;
    }

    public boolean isDown() {
        return down;
    }

}
