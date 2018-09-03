package com.rangga.tokokita.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
public class Cart {

    @Id
    private String _id;
    private String user_id;


    private List<CartSeller> cartSellers=new ArrayList<>();
    private int total_price = 0;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setCartSellers(List<CartSeller> cartSellers) {
        this.cartSellers = cartSellers;
    }

    public List<CartSeller> getCartSellers() {
        return cartSellers;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void addProduct(CartSeller product){
        this.cartSellers.add(product);
    }


}
