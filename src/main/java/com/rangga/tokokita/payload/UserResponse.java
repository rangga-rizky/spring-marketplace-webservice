package com.rangga.tokokita.payload;

import com.rangga.tokokita.model.Address;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class UserResponse implements Serializable {
    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private String email;
    @ApiModelProperty(position = 2)
    private List<Address> addresses;
    @ApiModelProperty(position = 3)
    private LocalDate birthdate;

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

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
}
