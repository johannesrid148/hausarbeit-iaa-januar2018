package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception that will be thrown if the user attempts to logout a user but this user is not logged in in the database
 *
 * @author Johannes Ridinger
 */
public class SelfLoggedOutException extends Exception {
    public SelfLoggedOutException(String message) {
        super(message);
    }
}
