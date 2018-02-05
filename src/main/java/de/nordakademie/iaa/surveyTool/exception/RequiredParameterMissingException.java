package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception which will be thrown when for an update or creation necessary parameters are missing
 *
 * @author Johannes Ridinger
 */
public class RequiredParameterMissingException extends Exception {
    public RequiredParameterMissingException(String message) {
        super(message);
    }
}
