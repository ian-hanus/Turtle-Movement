package frontend;

/**
 * Display an error in a command that the user entered with the desired string
 */
public interface ErrorDisplay {
    /**
     * Create a front-end pop up with the command error
     * @param errorMessage
     */
    void displayError(String errorMessage);
}
