package backend;

/**
 * Interface for the class that will represent a turtle. Defines the various fields of the turtle and provides
 * appropriate getter methods
 */
public interface Turtle {

    /**
     * Gets the x-coordinate of the turtle
     *
     * @return the x-coordinate
     */
    double getX();

    /**
     * Gets the y-coordinate of the turtle
     *
     * @return the y-coordinate
     */
    double getY();

    /**
     * Determines whether the pen was down or up for the turtle's most recent move
     *
     * @return true if the pen was down, false otherwise
     */
    boolean getPenState();

    /**
     * Gets the orientation of the turtle
     *
     * @return the angle, in degrees, of the turtle's heading
     */
    double getOrientation();

    /**
     * Determines whether the turtle should be visible or not
     *
     * @return true if the turtle is visible, false otherwise
     */
    boolean getVisibility();

    /**
     * Determines whether the turtle is resetting or not
     *
     * @return true if the turtle has just reset, false otherwise
     */
    boolean isReset();
}
