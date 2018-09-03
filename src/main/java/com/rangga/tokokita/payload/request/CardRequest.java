package com.rangga.tokokita.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CardRequest {
    @NotNull
    private String holder_name;
    @NotNull
    private String number;


    public CardRequest(){};

    public CardRequest(String holder_name, String number, LocalDate expired_data) {
        this.holder_name = holder_name;
        this.number = number;
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
}
