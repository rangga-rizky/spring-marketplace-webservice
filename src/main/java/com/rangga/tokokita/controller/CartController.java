package com.rangga.tokokita.controller;

import com.rangga.tokokita.exception.ResourceNotFoundExceptions;
import com.rangga.tokokita.exception.UnprocessableEntityException;
import com.rangga.tokokita.model.Cart;
import com.rangga.tokokita.model.CartProduct;
import com.rangga.tokokita.model.CartSeller;
import com.rangga.tokokita.model.Product;
import com.rangga.tokokita.payload.CartResponse;
import com.rangga.tokokita.payload.CartSellerResponse;
import com.rangga.tokokita.payload.ProductListResponse;
import com.rangga.tokokita.payload.UserResponse;
import com.rangga.tokokita.payload.common.PostResponse;
import com.rangga.tokokita.service.CartService;
import com.rangga.tokokita.service.ProductService;
import com.rangga.tokokita.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
@Api(tags = "cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("")
    @ApiOperation(value = "${CartController.index}")
    public List<CartSellerResponse> index(HttpServletRequest req) {
        Cart cart = cartService.getCartsByUser(userService.getCurrentUser(req).get_id());
        List<CartSellerResponse> response = new ArrayList<>();
        for(CartSeller cartSeller:cart.getCartSellers()){
            response.add(new CartSellerResponse(modelMapper.map(cartSeller.getSeller(), UserResponse.class),
                    cartSeller.getProducts().stream()
                            .map(cartProduct -> modelMapper.map(cartProduct, CartResponse.class))
                            .collect(Collectors.toList())));
        }
        return response;
    }

    @PutMapping("/add")
    @ApiOperation(value = "${CartController.add}")
    public PostResponse add(HttpServletRequest req,
                            @RequestBody Map<String, String> productRequest) {

        Product product = productService.findById(productRequest.get("product_id"));
        if(product == null){
            throw  new ResourceNotFoundExceptions("Produk tidak ditemukan");
        }
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartService.storeProduct(userService.getCurrentUser(req).get_id(),cartProduct);
        PostResponse response = new PostResponse(false,"Produk Berhasil ditambahkan ke keranjang");
        return response;
    }

    @PutMapping("/remove")
    @ApiOperation(value = "${CartController.remove}")
    public PostResponse remove(HttpServletRequest req,
                             @RequestBody  Map<String, String> productRequest) {
        String product_id = productRequest.get("product_id");
        if(product_id==null){
            throw new UnprocessableEntityException("Product id tidak boleh kosong");
        }
        Product product = productService.findById(productRequest.get("product_id"));
        cartService.removeProduct(userService.getCurrentUser(req).get_id(),product);
        PostResponse response = new PostResponse(false,"Produk Berhasil dihapus dari keranjang");
        return response;
    }

}
