package com.rangga.tokokita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenErrorException extends RuntimeException {

    public TokenErrorException(String message){
        super(message);
    }

}
