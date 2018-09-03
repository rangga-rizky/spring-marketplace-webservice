package com.rangga.tokokita.payload;

import com.rangga.tokokita.payload.common.PostResponse;

public class AuthResponse extends PostResponse {
    private String token;

    public AuthResponse(boolean error, String messages,String token) {
        super(error, messages);
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
