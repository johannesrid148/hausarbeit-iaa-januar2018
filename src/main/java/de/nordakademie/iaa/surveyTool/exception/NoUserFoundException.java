package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception that will be thrown if there is no user found but should be found
 *
 * @author Johannes Ridinger
 */
public class NoUserFoundException extends Exception{
    public NoUserFoundException(String message) {
        super(message);
    }
}
