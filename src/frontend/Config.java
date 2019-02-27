package frontend;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Stores the features of our simulation that should be able to be changed from a series of dropdowns and menus
 */
public class Config {
    Color turtleColor;
    Color penColor;
    Color backgroundColor;
    String language;
    Image turtleImage;


    public Image getTurtleImage() {
        return turtleImage;
    }

    public void setTurtleImage(Image turtleImage) {
        this.turtleImage = turtleImage;
    }



    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Get the current color of the turtle
     * @return a paint color corresponding to the current color of the turtle
     */
    Color getTurtleColor(){
        return turtleColor;
    }

    /**
     * Get the current color of the pen
     * @return a paint color corresponding to the current color of the pen
     */
    Color getPenColor(){
        return penColor;
    }

    /**
     * Reset the configuration to all of the defaults
     */
    void resetConfig(){}

    /**
     * Set the color of the turtle
     * @param c is the paint color of the turtle
     */
    void setTurtleColor(Color c){
        turtleColor = c;
    }

    /**
     * Set the color of the pen
     * @param c is the paint color of the pen
     */
    void setPenColor(Color c){
        penColor = c;
    }

    /**
     * Set the language that will be coded into to translate the commands
     * @param s is the string representing the language
     */
    void setLanguage(String s){
        //Change language shit
        language = s;
    }

}
