package com.rangga.tokokita.payload;

import com.rangga.tokokita.model.Address;
import com.rangga.tokokita.model.Card;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ProfilePayload {
    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    @NotNull
    private String email;
    @ApiModelProperty(position = 2)
    private List<Address> addresses;
    @ApiModelProperty(position = 3)
    @Valid
    private List<Card> cards;
    @ApiModelProperty(position = 4)
    private Date created_at;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
