package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception which will be thrown when user tries to create an already existing object
 *
 * @author Johannes Ridinger
 */
public class ObjectAlreadyExistsException extends Exception {
    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
