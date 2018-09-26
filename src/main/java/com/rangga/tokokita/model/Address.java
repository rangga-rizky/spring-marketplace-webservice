package com.rangga.tokokita.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Address implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
