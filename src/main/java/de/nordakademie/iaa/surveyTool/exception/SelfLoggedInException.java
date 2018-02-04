package de.nordakademie.iaa.surveyTool.exception;

public class SelfLoggedInException extends Exception {
    public SelfLoggedInException(String message) {
        super(message);
    }
}
