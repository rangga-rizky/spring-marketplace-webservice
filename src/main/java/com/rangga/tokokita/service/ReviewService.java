package com.rangga.tokokita.service;

import com.rangga.tokokita.model.Review;
import com.rangga.tokokita.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review save(Review review){
        review.setCreated_at(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review findByProductAndUser(String product_id,String user_id){
        Optional<Review> review = reviewRepository.findByProductAndUser(product_id,user_id);
        if(!review.isPresent()){
            return null;
        }
        return review.get();
    }
}
