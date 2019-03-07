package frontend;

public class TurtleState {

    private int id;
    private double x;
    private double y;
    private double angle;
    private boolean visible;
    private boolean down;
    private boolean shouldReset;

    public TurtleState(double xpos, double ypos, double orientation, boolean isVisible, boolean isDown){
        //id = i;
        x = xpos;
        y = ypos;
        angle = orientation;
        visible = isVisible;
        down = isDown;
        shouldReset = false;

    }

    public TurtleState(TurtleState toCopy){
        //this.id = toCopy.getID();
        this.x = toCopy.getX();
        this.y = toCopy.getY();
        this.angle = toCopy.getAngle();
        this.visible = toCopy.isVisible();
        this.down = toCopy.isDown();
    }

    public int getID(){return id;}
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

    public void setID(int id) {this.id = id;}
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setAngle(double angle) { this.angle = angle; }
    public void setVisibility(boolean visible) { this.visible = visible; }
    public void setDown(boolean down) { this.down = down; }
    public void setShouldReset(boolean shouldReset) { this.shouldReset = shouldReset; }





}
