package com.rangga.tokokita.payload.common;

import io.swagger.annotations.ApiModelProperty;

public class PostResponse {
    @ApiModelProperty(position = 0)
    private boolean error;
    @ApiModelProperty(position = 1)
    private String message;

    public PostResponse(Boolean error, String messages) {
        this.error = error;
        this.message = messages;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
