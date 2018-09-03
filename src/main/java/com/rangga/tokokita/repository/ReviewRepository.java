package com.rangga.tokokita.repository;

import com.rangga.tokokita.model.Cart;
import com.rangga.tokokita.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{ 'product_id': ?0, 'user_id': ?1}")
    public Optional<Review> findByProductAndUser(String product_id, String user_id);
}
