package com.rangga.tokokita.controller;

import com.rangga.tokokita.exception.NotResourceOwnerExceptions;
import com.rangga.tokokita.model.*;
import com.rangga.tokokita.payload.CartResponse;
import com.rangga.tokokita.payload.OrderResponse;
import com.rangga.tokokita.payload.common.PostResponse;
import com.rangga.tokokita.payload.request.CardRequest;
import com.rangga.tokokita.payload.request.OrderRequest;
import com.rangga.tokokita.service.CartService;
import com.rangga.tokokita.service.OrderService;
import com.rangga.tokokita.service.PaymentService;
import com.rangga.tokokita.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@Api(tags = "order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ModelMapper modelMapper;


    //aasumsikan satu payment hanaya bisa satu shipping method
    @PostMapping("")
    @ApiOperation(value = "${OrderController.create}")
    public PostResponse create(HttpServletRequest req,
                               @Valid @RequestBody OrderRequest orderRequest){

        String user_id = userService.getCurrentUser(req).get_id();
        Cart cart = cartService.getCartsByUser(user_id);
        List<Order> orders= new ArrayList<>();
        int total_price = 0;
        for(CartSeller cartSeller:cart.getCartSellers()){
            Order newOrder = new Order();
            newOrder.setShipping_method(orderRequest.getShipping());
            newOrder.setUser_id(user_id);
            newOrder.setSeller_id(cartSeller.getSeller().get_id());
            newOrder.setProducts(cartSeller.getProducts());
            newOrder = orderService.create(newOrder);
            orders.add(newOrder);
            total_price = total_price + newOrder.getTotal_price();
        }
        total_price = total_price+orderRequest.getShipping().getPrice();
        Payment payment = new Payment();
        payment.setOrders(orders);
        payment.setPayment_method(modelMapper.map(orderRequest.getPayment_method(),Card.class) );
        payment.setTotal_price(total_price);
        paymentService.create(payment);
        cart.setCartSellers(new ArrayList<>());
        cartService.create(cart);
        return new PostResponse(false,"Pesanan Berhasil dibuat");

    }

    @GetMapping("")
    @ApiOperation(value = "${OrderController.index}")
    public List<OrderResponse> showOrderByUser(HttpServletRequest req){
        List<Order> orders = orderService.showOrderByUser(userService.getCurrentUser(req).get_id());
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/seller")
    @ApiOperation(value = "${OrderController.seller}")
    public List<OrderResponse> showOrderBySeller(HttpServletRequest req){
        List<Order> orders = orderService.showOrderBySeller(userService.getCurrentUser(req).get_id());
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/accept")
    @ApiOperation(value = "${OrderController.accept}")
    public PostResponse acceptOrder(@PathVariable("id") String id,
                            HttpServletRequest req){
        Order order = orderService.findByID(id);
        if(!order.getSeller_id().equals(userService.getCurrentUser(req).get_id())){
            throw new NotResourceOwnerExceptions("Anda tidak diizinkan mengakses resource ini");
        }
        order.setStatus(OrderStatus.ACCEPTED.name());
        orderService.save(order);
        return new PostResponse(false,"Order Berhasil Diterima");
    }

    @PutMapping("/{id}/delivered")
    @ApiOperation(value = "${OrderController.delivered}")
    public PostResponse changeOrderDelivered(@PathVariable("id") String id,
                                    HttpServletRequest req){
        Order order = orderService.findByID(id);
        if(!order.getSeller_id().equals(userService.getCurrentUser(req).get_id())){
            throw new NotResourceOwnerExceptions("Anda tidak diizinkan mengakses resource ini");
        }
        order.setStatus(OrderStatus.DELIVERED.name());
        orderService.save(order);
        return new PostResponse(false,"Order Status Berhasil di ubah");
    }
}
