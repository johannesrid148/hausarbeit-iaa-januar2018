package de.nordakademie.iaa.surveyTool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerSurvey {

    @ExceptionHandler(value = {InstanceAlreadyBuildException.class, ParameterMissingException.class,
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
