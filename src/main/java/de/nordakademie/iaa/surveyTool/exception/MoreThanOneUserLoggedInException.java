package de.nordakademie.iaa.surveyTool.exception;

public class MoreThanOneUserLoggedInException extends Exception {
    public MoreThanOneUserLoggedInException(String message) {
        super(message);
    }
}
