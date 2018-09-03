package com.rangga.tokokita.controller;


import com.rangga.tokokita.model.Order;
import com.rangga.tokokita.model.Product;
import com.rangga.tokokita.model.Review;
import com.rangga.tokokita.model.User;
import com.rangga.tokokita.payload.common.PostResponse;
import com.rangga.tokokita.payload.request.ReviewRequest;
import com.rangga.tokokita.service.ProductService;
import com.rangga.tokokita.service.ReviewService;
import com.rangga.tokokita.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/reviews")
@Api(tags = "review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping("")
    @ApiOperation(value = "${ReviewController.create}")
    public PostResponse create(@Valid @RequestBody ReviewRequest review, HttpServletRequest req){
        User user =  userService.getCurrentUser(req);
        Product product = productService.findById(review.getProduct_id());
        Review curentReview = reviewService.findByProductAndUser(review.getProduct_id(),user.get_id());
        if(curentReview == null){
            curentReview = new Review();
        }
        curentReview.setRating(review.getRating());
        curentReview.setReview(review.getReview());
        curentReview.setUser_id(user.get_id());
        curentReview.setUser_name(user.getUsername());
        curentReview.setProduct_id(review.getProduct_id());
        product.getReviews().add(curentReview);
        reviewService.save(curentReview);
        productService.save(product);
        return  new PostResponse(false,"Review Berhasil ditambahkan");
    }
}
