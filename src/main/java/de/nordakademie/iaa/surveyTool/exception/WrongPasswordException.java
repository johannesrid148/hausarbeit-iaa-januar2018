package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception that will be thrown if the user attempts to login a user but the password
 * saved in the database for this user does not match the entered password
 *
 * @author Johannes Ridinger
 */
public class WrongPasswordException extends Exception {
    public WrongPasswordException(String message) {
        super(message);
    }
}
