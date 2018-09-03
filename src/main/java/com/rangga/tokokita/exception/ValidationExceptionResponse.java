package com.rangga.tokokita.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationExceptionResponse extends ExceptionResponse {
    private List<String> errors = new ArrayList<>();

    public ValidationExceptionResponse(Date timestamp, String message, String detail,List<String> errors) {
        super(timestamp, message, detail);
        this.errors = errors;

    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
