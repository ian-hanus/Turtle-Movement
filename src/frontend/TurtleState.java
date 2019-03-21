package frontend;

public class TurtleState {

    private double id;
    private double x;
    private double y;
    private double angle;
    private double background;
    private double penColor;
    private double penSize;
    private double shape;
    private boolean visible;
    private boolean down;
    private boolean shouldReset;
    private static final int STARTING_ANGLE = 90;

    /**
     * default constructor
     */
    public TurtleState(){
        this.x = 0;
        this.y = 0;
        this.angle = STARTING_ANGLE;
        this.visible = true;
        this.down = true;
        this.shouldReset = false;
        this.id = 1;
        this.background = 0;
        this.penColor = 0;
        this.penSize = 0;
        this.shape = 0;

    }

    /**
     * constructor for creating a new state
     * @param xpos the x position
     * @param ypos the y position
     * @param orientation angle heading
     * @param isVisible whether it's visible
     * @param isDown state of the pen
     * @param id number associated with this sprite
     * @param background background color
     * @param penColor pen color
     * @param penSize pen size
     * @param shape shape
     */
    public TurtleState(double xpos, double ypos, double orientation, boolean isVisible,
                       boolean isDown, double id, double background, double penColor,
                       double penSize, double shape) {
        this.x = xpos;
        this.y = ypos;
        this.angle = orientation;
        this.visible = isVisible;
        this.down = isDown;
        this.shouldReset = false;
        this.id = id;
        this.background = background;
        this.penColor = penColor;
        this.penSize = penSize;
        this.shape = shape;
    }

    /**
     * constrcutro that returns an identical state to the parameter given
     * @param toCopy the state to copy
     */
    public TurtleState(TurtleState toCopy) {
        this.x = toCopy.getX();
        this.y = toCopy.getY();
        this.angle = toCopy.getAngle();
        this.visible = toCopy.isVisible();
        this.down = toCopy.isDown();
        this.shouldReset = false;
        this.id = toCopy.getID();
        this.background = toCopy.getBackground();
        this.penColor = toCopy.getPenColor();
        this.penSize = toCopy.getPenSize();
        this.shape = toCopy.getShape();
    }

    /**
     * constructor that allows for the copying of a turtlestate from another, with rounded values
     * @param toCopy the state which to copy from
     * @param display a flag
     */
    public TurtleState(TurtleState toCopy, boolean display){

        this.x = .01 * Math.floor(((toCopy.getX()*100)));
        this.y = .01 * Math.floor(((toCopy.getY()*100)));
        this.angle = .01 * Math.floor(((toCopy.getAngle()*100)));
        this.visible = toCopy.isVisible();
        this.down = toCopy.isDown();
        this.shouldReset = false;
        this.id = toCopy.getID();
        this.background = toCopy.getBackground();
        this.penColor = toCopy.getPenColor();
        this.penSize = toCopy.getPenSize();
        this.shape = toCopy.getShape();

    }

    /**
     * The remainder of methods are standard getters and setters. The instance variables are manipulated mostly at the turtlesprite level and are not often modifiable
     * @return
     */

    public double getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isVisible() {
        return visible;
    }

    public double getBackground() {
        return background;
    }

    public void setBackground(double background) {
        this.background = background;
    }

    public double getPenColor() {
        return penColor;
    }

    public void setPenColor(double penColor) {
        this.penColor = penColor;
    }

    public double getPenSize() {
        return penSize;
    }

    public void setPenSize(double penSize) {
        this.penSize = penSize;
    }

    public double getShape() {
        return shape;
    }

    public void setShape(double shape) {
        this.shape = shape;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean shouldReset() {
        return shouldReset;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    public void setShouldReset(boolean shouldReset) {
        this.shouldReset = shouldReset;
    }


}
