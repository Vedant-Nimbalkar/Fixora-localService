package com.localservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.localservice.model.Booking;
import com.localservice.repository.BookingRepository;

@Controller  // ADD THIS
public class PageController {

    @Autowired  // ADD THIS
    private BookingRepository bookingRepository;

    @GetMapping("/payment-page/{bookingId}")
    public String paymentPage(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        model.addAttribute("bookingId", bookingId);
        model.addAttribute("serviceType", booking.getServiceType());
        model.addAttribute("bookingDate", booking.getBookingDate());
        model.addAttribute("timeSlot", booking.getTimeSlot());

        return "payment-page";
    }
}