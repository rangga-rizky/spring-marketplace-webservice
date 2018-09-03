package com.rangga.tokokita.service;

import com.rangga.tokokita.exception.ResourceNotFoundExceptions;
import com.rangga.tokokita.model.CartProduct;
import com.rangga.tokokita.model.Category;
import com.rangga.tokokita.model.Order;
import com.rangga.tokokita.model.OrderStatus;
import com.rangga.tokokita.repository.CategoryRepository;
import com.rangga.tokokita.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order create(Order order) {
        order.setCreated_data(LocalDateTime.now());
        int total_amount = 0,total_price = 0;
        for(CartProduct cartProduct:order.getProducts()){
            total_amount = total_amount + cartProduct.getQty();
            total_price = total_price + cartProduct.getProduct().getPrice();
        }
        order.setStatus(OrderStatus.WAITING.name());
        order.setTotal_amount(total_amount);
        order.setTotal_price(total_price);
        return orderRepository.save(order);
    }

    public List<Order> showOrderBySeller(String seller_id){
        return orderRepository.findBySeller(seller_id);
    }
    public List<Order> showOrderByUser(String user_id){
        return orderRepository.findByUser(user_id);
    }


    public Order findByID(String id){
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent()){
            throw new ResourceNotFoundExceptions("Order tidak ditemukan");
        }
        return order.get();
    }



}
