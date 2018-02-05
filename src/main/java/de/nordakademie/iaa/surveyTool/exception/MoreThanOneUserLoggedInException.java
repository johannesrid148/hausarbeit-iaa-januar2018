package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception that will be thrown if there is more than one user logged in in the database
 *
 * @author Johannes Ridinger
 */
public class MoreThanOneUserLoggedInException extends Exception {
    public MoreThanOneUserLoggedInException(String message) {
        super(message);
    }
}
