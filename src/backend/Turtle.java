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
    int getX();

    /**
     * Gets the y-coordinate of the turtle
     *
     * @return the y-coordinate
     */
    int getY();

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
    int getOrientation();

    /**
     * Determines whether the turtle should be visible or not
     *
     * @return true if the turtle is visible, false otherwise
     */
    boolean getVisibility();
}
