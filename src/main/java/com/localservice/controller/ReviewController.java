package com.localservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.localservice.model.Review;
import com.localservice.model.User;
import com.localservice.repository.ReviewRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {

	@Autowired
	private ReviewRepository reviewRepository;

	@GetMapping("/review/{partnerId}")
	public String reviewForm(@PathVariable Long partnerId, Model model) {

		Review review = new Review();
		review.setPartnerId(partnerId);

		model.addAttribute("review", review);

		return "review-form";
	}

//    @PostMapping("/review/save")
//    public String saveReview(@ModelAttribute Review review, HttpSession session){
//
//        User user = (User) session.getAttribute("loggedUser");
//
//        review.setUserEmail(user.getEmail());
//
//        reviewRepository.save(review);
//
//        return "redirect:/my-bookings";
//    }
	@PostMapping("/review/save")
	public String saveReview(@ModelAttribute Review review, HttpSession session) {

		User user = (User) session.getAttribute("loggedUser");
		review.setUserEmail(user.getEmail());
		reviewRepository.save(review);
		return "redirect:/my-bookings";
	}
}
