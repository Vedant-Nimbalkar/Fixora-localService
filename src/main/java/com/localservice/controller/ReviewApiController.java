package com.localservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.localservice.model.Review;
import com.localservice.repository.ReviewRepository;

@RestController
@RequestMapping("/api")
public class ReviewApiController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public List<Review> getReviews() {
        return reviewRepository.findTop6ByOrderByIdDesc();
    }
}