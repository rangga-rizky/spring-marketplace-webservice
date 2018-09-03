package com.rangga.tokokita.service;

import com.rangga.tokokita.model.*;
import com.rangga.tokokita.repository.CartRepository;
import com.rangga.tokokita.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void create(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart getCartsByUser(String user_id){
        return cartRepository.findByUserId(user_id);
    }

    public void storeProduct(String user_id, CartProduct newProduct){
        Cart cart = cartRepository.findByUserId(user_id);
        for(CartSeller cartSeller:cart.getCartSellers()){
            if(cartSeller.getProducts().get(0).getProduct().getSeller().get_id()
                    .equals(newProduct.getProduct().getSeller().get_id())){
                for(CartProduct cartProduct:cartSeller.getProducts()){
                    if(cartProduct.getProduct().get_id().equals(newProduct.getProduct().get_id())){
                        cartProduct.setQty(cartProduct.getQty()+1);
                        cartRepository.save(cart);
                        return;
                    }
                }
                cartSeller.getProducts().add(newProduct);
                cartRepository.save(cart);
                return;
            }else{
                for(CartProduct cartProduct:cartSeller.getProducts()){
                    if(cartProduct.getProduct().get_id().equals(newProduct.getProduct().get_id())){
                        cartProduct.setQty(cartProduct.getQty()+1);
                        cartRepository.save(cart);
                        return;
                    }
                }
            }
        }
        CartSeller newCartSeller = new CartSeller();
        List<CartProduct> newCartProducts = new ArrayList<>();
        newCartProducts.add(newProduct);
        newCartSeller.setSeller(newProduct.getProduct().getSeller());
        newCartSeller.setProducts(newCartProducts);
        cart.getCartSellers().add(newCartSeller);
        cartRepository.save(cart);
        return;

    }

    public void  removeProduct(String user_id, Product productToDelete){
        Cart cart = cartRepository.findByUserId(user_id);
        for(CartSeller cartSeller:cart.getCartSellers()){
            if(cartSeller.getProducts().get(0).getProduct().getSeller().get_id()
                    .equals(productToDelete.getSeller().get_id())){
                for(CartProduct cartProduct:cartSeller.getProducts()){
                    if(cartProduct.getProduct().get_id().equals(productToDelete.get_id())){
                        cartSeller.getProducts().remove(cartProduct);
                        cartRepository.save(cart);
                        return;
                    }
                }
            }
        }
    }

}
