package de.nordakademie.iaa.surveyTool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionHandler {
    public ExceptionHandler() {
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({InstanceAlreadyBuildException.class, ParameterMissingException.class,
            WrongParameterValuesException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(e.getMessage());
    }


    private class ErrorResponse {
        private String errorMessage;

        public ErrorResponse(Object message) {
        }
    }

}
