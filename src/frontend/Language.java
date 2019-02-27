package frontend;

public enum Language {
    CHINESE("Chinese"), ENGLISH("English"), FRENCH("French"), GERMAN("German"), ITALIAN("Italian"), PORTUGUESE("Portuguese"),
    RUSSIAN("Russian"), SPANISH("Spanish"), URDU("Urdu");

    private String myLabel;

    Language(String label){
        myLabel = label;
    }

    @Override
    public String toString(){
        return myLabel;
    }
}
