package com.localservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByPartnerId(Long partnerId);

	List<Booking> findByUserEmail(String userEmail);
	
}
