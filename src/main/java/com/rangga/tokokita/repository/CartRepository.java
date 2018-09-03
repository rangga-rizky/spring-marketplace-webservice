package com.rangga.tokokita.repository;

import com.rangga.tokokita.model.Cart;
import com.rangga.tokokita.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {

    @Query("{'user_id': ?0}")
    Cart findByUserId(String user_id);
}
