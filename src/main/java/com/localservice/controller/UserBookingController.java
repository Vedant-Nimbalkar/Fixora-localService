//package com.localservice.controller;
//
//import com.localservice.model.Booking;
//import com.localservice.model.User;
//import com.localservice.repository.BookingRepository;
//
//import jakarta.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class UserBookingController {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @GetMapping("/my-bookings")
//    public String myBookings(HttpSession session, Model model){
//
//        User user = (User) session.getAttribute("loggedUser");
//
//        if(user == null){
//            return "redirect:/login";
//        }
//
//        model.addAttribute(
//            "bookings",
//            bookingRepository.findByUserEmail(user.getEmail())
//        );
//
//        return "my-bookings";
//    }
//
//}

//package com.localservice.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.localservice.model.Booking;
//import com.localservice.model.User;
//import com.localservice.repository.BookingRepository;
//
//import jakarta.servlet.http.HttpSession;
//
//// Change @Controller to @RestController
//@RestController
//@RequestMapping("/api")
//public class UserBookingController {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @GetMapping("/my-bookings")
//    public List<Booking> myBookings(HttpSession session) {
//        User user = (User) session.getAttribute("loggedUser");
//        if (user == null) {
//            return List.of();
//        }
//        return bookingRepository.findByUserEmail(user.getEmail());
//    }
//}

package com.localservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.localservice.model.Booking;
import com.localservice.repository.BookingRepository;

@RestController
@RequestMapping("/api")
public class UserBookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/my-bookings")
    public List<Booking> myBookings(@RequestParam String userEmail) {
        if (userEmail == null || userEmail.isBlank()) return List.of();
        return bookingRepository.findByUserEmail(userEmail);
    }
}