//package com.localservice.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.localservice.model.Booking;
//import com.localservice.model.User;
//import com.localservice.repository.BookingRepository;
//import com.localservice.repository.ServicePartnerRepository;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class ServiceController {
//
//    @Autowired
//    private ServicePartnerRepository servicePartnerRepository;
//
//    // 🔹 Add this repository (THIS WAS MISSING)
//    @Autowired
//    private BookingRepository bookingRepository;
//
//
//    // Show services page
//    @GetMapping("/services")
//    public String services(Model model, HttpSession session){
//
//        if(session.getAttribute("loggedUser") == null){
//            return "redirect:/login";
//        }
//
//        model.addAttribute("services", servicePartnerRepository.findAll());
//
//        return "services";
//    }
//
//
//    // Show booking form
//    @GetMapping("/booking/form/{partnerId}")
//    public String bookingForm(@PathVariable Long partnerId, Model model){
//
//        Booking booking = new Booking();
//        booking.setPartnerId(partnerId);
//
//        model.addAttribute("booking", booking);
//
//        return "booking-form";
//    }
//
//
//
//}

package com.localservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.localservice.model.Booking;
import com.localservice.repository.BookingRepository;
import com.localservice.repository.ServicePartnerRepository;

@Controller
public class ServiceController {

    @Autowired
    private ServicePartnerRepository servicePartnerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // ❌ REMOVE @GetMapping("/services") — React handles this now

    // Keep this — React booking slot needs it
    @GetMapping("/booking/form/{partnerId}")
    public String bookingForm(@PathVariable Long partnerId, Model model) {
        Booking booking = new Booking();
        booking.setPartnerId(partnerId);
        model.addAttribute("booking", booking);
        return "booking-form";
    }
}