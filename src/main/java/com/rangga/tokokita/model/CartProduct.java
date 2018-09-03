package com.rangga.tokokita.model;

import com.rangga.tokokita.payload.UserResponse;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class CartProduct{

    private int qty=1;

    @DBRef
    private Product product;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
