package com.localservice.repository;

import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localservice.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findTop6ByOrderByIdDesc(); // fetch latest 6 reviews
	List<Review> findByPartnerId(Long partnerId);
}
