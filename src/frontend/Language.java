package frontend;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Language {
    CHINESE("chinese"), ENGLISH("english"), FRENCH("french"), GERMAN("german"), ITALIAN("italian"), PORTUGUESE("portuguese"),
    RUSSIAN("russian"), SPANISH("spanish"), URDU("urdu");

    private String myLabel;

    Language(String label){
        myLabel = label;
    }

    @Override
    public String toString(){
        return myLabel;
    }
}
