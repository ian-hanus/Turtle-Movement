package frontend;

public class TurtleState {

    private double x;
    private double y;
    private double angle;
    private boolean visible;
    private boolean down;
    private boolean shouldReset;

    public TurtleState(double xpos, double ypos, double orientation, boolean isVisible, boolean isDown){
        x = xpos;
        y = ypos;
        angle = orientation;
        visible = isVisible;
        down = isDown;
        shouldReset = false;
    }

    public TurtleState(TurtleState toCopy){
        this.x = toCopy.getX();
        this.y = toCopy.getY();
        this.angle = toCopy.getAngle();
        this.visible = toCopy.isVisible();
        this.down = toCopy.isDown();
    }

    public double getX() {
        return x;
    }
    public double getY() {
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
    public boolean shouldReset() { return shouldReset; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setAngle(double angle) { this.angle = angle; }
    public void setVisibility(boolean visible) { this.visible = visible; }
    public void setDown(boolean down) { this.down = down; }
    public void setShouldReset(boolean shouldReset) { this.shouldReset = shouldReset; }





}
