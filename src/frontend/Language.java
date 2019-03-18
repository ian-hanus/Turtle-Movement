package frontend;

/**
 * Enumeration of all languages allowed as input, and the strings that correspond to them
 */
public enum Language {
    CHINESE("chinese"), ENGLISH("english"), FRENCH("french"), GERMAN("german"), ITALIAN("italian"), PORTUGUESE("portuguese"),
    RUSSIAN("russian"), SPANISH("spanish"), URDU("urdu");

    private String myLabel;

    Language(String label){
        myLabel = label;
    }

    /**
     * Gets the string corresponding to the Language
     * @return the string form of the language enumeration
     */
    @Override
    public String toString(){
        return myLabel;
    }
}
