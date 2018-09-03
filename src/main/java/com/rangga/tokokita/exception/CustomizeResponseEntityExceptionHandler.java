package com.rangga.tokokita.exception;

import com.rangga.tokokita.payload.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                    new Date(),
                    e.getMessage(),
                    request.getDescription(false)
                );
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(TokenErrorException.class)
    public ResponseEntity<Object> handleTokenError(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotResourceOwnerExceptions.class)
    public final ResponseEntity<Object> handleNotResourceOwnerException(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(
                new Date(),
                "Validation Failed",
                request.getDescription(false),
                errors
        );
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " harus ada.";
        ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(
                new Date(),
                "Validation Failed",
                request.getDescription(false),
                Arrays.asList(error)
        );
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        try {
            List<String> messages = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(
                    new Date(),
                    "Validation Failed",
                    "Constrain Violation",
                    messages
            );

            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(
                    new Date(),
                    "Validation Failed",
                    "Constrain Violation",
                    Arrays.asList(ex.getMessage())
            );

            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }
    }


}
