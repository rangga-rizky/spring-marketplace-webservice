package com.rangga.tokokita.payload.common;

public class CreatedResponse extends PostResponse {
    private String object_id;

    public CreatedResponse(boolean error, String messages, String object_id) {
        super(error, messages);
        this.object_id = object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public String getObject_id() {
        return object_id;
    }
}
