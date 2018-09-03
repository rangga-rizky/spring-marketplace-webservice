package com.rangga.tokokita.repository;

import com.rangga.tokokita.model.Cart;
import com.rangga.tokokita.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{'seller_id': ?0}")
    List<Order> findBySeller (String seller_id);

    @Query("{'user_id': ?0}")
    List<Order> findByUser (String user_id);
}
