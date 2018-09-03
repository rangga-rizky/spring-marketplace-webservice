package com.rangga.tokokita.payload;

import com.rangga.tokokita.model.Category;
import com.rangga.tokokita.model.Review;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductListResponse {

    private String _id;

    private UserResponse seller;

    private String picture;

    private String name;
    private int price = 0;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public UserResponse getSeller() {
        return seller;
    }

    public void setSeller(UserResponse seller) {
        this.seller = seller;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
