package de.nordakademie.iaa.surveyTool.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
*Klasse geschrieben von Max Schumann
 *
 * handles the Exeptions
*
* */
@ControllerAdvice
public class ExceptionHandlerSurvey {

    @ExceptionHandler(value = {AnotherUserLoggedInException.class,MoreThanOneUserLoggedInException.class,
            NoUserFoundException.class,ObjectAlreadyExistsException.class,RequiredParameterMissingException.class,SelfLoggedInException.class,
            SelfLoggedOutException.class,SurveyNotFoundException.class,WrongAccessDataException.class,
            WrongPasswordException.class,InstanceAlreadyBuildException.class, ParameterMissingException.class,
            WrongParameterValuesException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

    @AllArgsConstructor
    @Getter
    private class ErrorResponse {
        private String errorMessage;

        public ErrorResponse(Object message) {
        }
    }

}
