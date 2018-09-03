package com.rangga.tokokita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotResourceOwnerExceptions extends RuntimeException {

    public NotResourceOwnerExceptions(String message){
        super(message);
    }

}
