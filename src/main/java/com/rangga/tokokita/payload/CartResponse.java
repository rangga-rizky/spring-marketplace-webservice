package com.rangga.tokokita.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.rangga.tokokita.model.Product;

public class CartResponse {

    private int qty;

    @JsonProperty("product")
    private ProductListResponse product;

    public CartResponse(int qty, ProductListResponse productResponse) {
        this.qty = qty;
        this.product = productResponse;
    }

    public CartResponse(){};

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setProduct(ProductListResponse product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public ProductListResponse getProduct() {
        return product;
    }
}
