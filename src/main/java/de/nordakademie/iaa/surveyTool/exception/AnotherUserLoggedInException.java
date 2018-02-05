package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception that will be thrown if the user attempts to login a user when there is already another user logged in in the database
 *
 * @author Johannes Ridinger
 */
public class AnotherUserLoggedInException extends Exception {
    public AnotherUserLoggedInException(String message) {
        super(message);
    }
}
