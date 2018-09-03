package com.rangga.tokokita.model;

public enum OrderStatus {
    WAITING("Waiting"),
    ACCEPTED("Accepted"),
    DELIVERED("Delivered");

    private String status;
    private OrderStatus(String status){
        this.status = status;
    }
}
