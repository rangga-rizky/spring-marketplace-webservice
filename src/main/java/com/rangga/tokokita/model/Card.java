package com.rangga.tokokita.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class Card {
    @NotNull
    private String holder_name;
    @NotNull
    private String number;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expired_date;

    public Card(){};

    public Card(String holder_name, String number, LocalDate expired_data) {
        this.holder_name = holder_name;
        this.number = number;
        this.expired_date = expired_data;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpired_data() {
        return expired_date;
    }

    public void setExpired_data(LocalDate expired_data) {
        this.expired_date = expired_data;
    }
}
