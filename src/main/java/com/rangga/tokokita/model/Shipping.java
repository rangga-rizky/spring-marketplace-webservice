package com.rangga.tokokita.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


public class Shipping {

    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
