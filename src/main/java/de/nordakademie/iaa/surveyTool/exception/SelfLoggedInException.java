package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception that will be thrown if the user attempts to login a user but this user is already logged in in the database
 *
 * @author Johannes Ridinger
 */
public class SelfLoggedInException extends Exception {
    public SelfLoggedInException(String message) {
        super(message);
    }
}
