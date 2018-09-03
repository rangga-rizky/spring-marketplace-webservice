package com.rangga.tokokita.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CartSellerResponse {

    private UserResponse seller;

    private List<CartResponse> products = new ArrayList<>();

    public CartSellerResponse(UserResponse seller, List<CartResponse> products) {
        this.seller = seller;
        this.products = products;
    }

    public void setProducts(List<CartResponse> products) {
        this.products = products;
    }

    public void setSeller(UserResponse seller) {
        this.seller = seller;
    }

    public UserResponse getSeller() {
        return seller;
    }

    public List<CartResponse> getProducts() {
        return products;
    }
}
