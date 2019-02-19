package frontend;

import javafx.scene.paint.Color;

/**
 * Stores the features of our simulation that should be able to be changed from a series of dropdowns and menus
 */
public interface Config {

    default public Color getTurtleColor(){
        return this.getTurtleColor();
    }

    default public Color getPenColor(){
        return this.getPenColor();
    }

    default public void resetConfig(){
        this.resetConfig();
    }

//    default public void setTurtleColor(Color c){
//        this.setTurtleColor(c);
//    }
//
//    default public void setPenColor(Color c){
//        this.setPenColor(c);
//    }
//
//    default public void setLanguage(String s){
//        this.setLanguage(s);
//    }

}
