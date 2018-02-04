package de.nordakademie.iaa.surveyTool.exception;

/**
 * Exception which will be thrown when user tries to create an already existing object
 *
 * @author Katja Niklas
 */
public class ObjectAlreadyExistsException extends Exception {
    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
