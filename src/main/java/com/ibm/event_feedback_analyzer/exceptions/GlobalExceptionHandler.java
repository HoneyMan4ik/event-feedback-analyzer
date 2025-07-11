package com.ibm.event_feedback_analyzer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
        public ResponseEntity<ErrorObject> handleEventNotFoundException(EventNotFoundException ex, WebRequest request){
            ErrorObject errorObject = new ErrorObject();

            errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
            errorObject.setMessage(ex.getMessage());
            errorObject.setTimestamp(new Date());

            return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<ErrorObject> handleBadRequestException(BadRequestException ex){
            ErrorObject errorObject = new ErrorObject();

            errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
            errorObject.setMessage(ex.getMessage());
            errorObject.setTimestamp(new Date());

            return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
        }
    }
