package frontend;

public class TurtleState {

    private int x;
    private int y;
    private double angle;
    private boolean visible;
    private boolean down;
    private boolean shouldReset;

    public TurtleState(int xpos, int ypos, double orientation, boolean isVisible, boolean isDown){
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
        this.shouldReset = toCopy.shouldReset();
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
    public boolean shouldReset() { return shouldReset; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setAngle(double angle) { this.angle = angle; }
    public void setVisibility(boolean visible) { this.visible = visible; }
    public void setDown(boolean down) { this.down = down; }
    public void setShouldReset(boolean shouldReset) { this.shouldReset = shouldReset; }





}
