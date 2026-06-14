package com.localservice.controller;

import com.localservice.model.ServicePartner;
import com.localservice.model.ServicePartnerRequest;
import com.localservice.repository.BookingRepository;
import com.localservice.repository.ServicePartnerRepository;
import com.localservice.service.ServicePartnerService;
import com.localservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/partner")
public class ServicePartnerController {

	@Autowired
	private ServicePartnerService servicePartnerService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ServicePartnerRepository servicePartnerRepository;

	// Upload directory
	private static final String UPLOAD_DIR = "src/main/resources/static/uploads/partners/";

	// Partner registration POST — handles file uploads
	@PostMapping("/register")
	public String registerPartner(@RequestParam String name,
			@RequestParam String email,
			@RequestParam String phone,
			@RequestParam String serviceType, 
			@RequestParam String password, 
			@RequestParam int experience,
			@RequestParam(required = false) String city, 
			@RequestParam(required = false) MultipartFile aadhaar,
			@RequestParam(required = false) MultipartFile pan, 
			@RequestParam(required = false) MultipartFile photo) {

		ServicePartnerRequest request = new ServicePartnerRequest();
		request.setName(name);
		request.setEmail(email);
		request.setPhone(phone);
		request.setServiceType(serviceType);
		request.setExperience(experience);
		request.setPassword(password); // ← add this before servicePartnerService.submitRequest(request)
		request.setCity(city);
		System.out.println("City received: " + city);

		// Create upload directory if it doesn't exist
		File uploadDir = new File(UPLOAD_DIR);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save Aadhaar
		if (aadhaar != null && !aadhaar.isEmpty()) {
			String aadhaarFile = saveFile(aadhaar);
			request.setAadhaarPath(aadhaarFile);
		}

		// Save PAN
		if (pan != null && !pan.isEmpty()) {
			String panFile = saveFile(pan);
			request.setPanPath(panFile);
		}

		// Save Photo
		if (photo != null && !photo.isEmpty()) {
			String photoFile = saveFile(photo);
			request.setPhotoPath(photoFile);
		}

		servicePartnerService.submitRequest(request);
		return "redirect:/partner/waiting";
	}

	// Helper method to save file
	private String saveFile(MultipartFile file) {
		try {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR + fileName);
			Files.write(filePath, file.getBytes());
			return "/uploads/partners/" + fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Partner login page GET
	@GetMapping("/login")
	public String partnerLoginPage() {
		return "partner-login";
	}

	// Partner login POST
	@PostMapping("/login")
	public String partnerLogin(@RequestParam String email, @RequestParam String password, Model model,
			HttpSession session) {

		ServicePartner partner = servicePartnerService.login(email, password);

		if (partner == null) {
			model.addAttribute("error", "Invalid credentials");
			return "partner-login";
		}

		session.setAttribute("loggedPartner", partner);
		return "redirect:/partner/dashboard";
	}

	// Partner dashboard
	@GetMapping("/dashboard")
	public String partnerDashboard(HttpSession session, Model model) {
		ServicePartner partner = (ServicePartner) session.getAttribute("loggedPartner");
		if (partner == null) {
			return "redirect:/partner/login";
		}
		model.addAttribute("bookings", bookingRepository.findByPartnerId(partner.getId()));
		return "partner-dashboard";
	}

	// Waiting page
	@GetMapping("/waiting")
	public String waitingPage() {
		return "waiting";
	}

	// Accept booking
	@GetMapping("/booking/accept/{id}")
	public String acceptBooking(@PathVariable Long id) {
		bookingRepository.findById(id).ifPresent(booking -> {
			booking.setStatus("CONFIRMED");
			bookingRepository.save(booking);
		});
		return "redirect:/partner/dashboard";
	}

	// Start work
	@GetMapping("/booking/start/{id}")
	public String startWork(@PathVariable Long id) {
		bookingRepository.findById(id).ifPresent(booking -> {
			booking.setStatus("IN_PROGRESS");
			bookingRepository.save(booking);
		});
		return "redirect:/partner/dashboard";
	}

	// Complete work
	@GetMapping("/booking/complete/{id}")
	public String completeWork(@PathVariable Long id) {
		bookingRepository.findById(id).ifPresent(booking -> {
			booking.setStatus("COMPLETED");
			bookingRepository.save(booking);
		});
		return "redirect:/partner/dashboard";
	}

	// Reject booking
	@GetMapping("/booking/reject/{id}")
	public String rejectBooking(@PathVariable Long id) {
		bookingRepository.findById(id).ifPresent(booking -> {
			booking.setStatus("REJECTED");
			bookingRepository.save(booking);
		});
		return "redirect:/partner/dashboard";
	}

	@GetMapping("/logout")
	public String partnerLogout(HttpSession session) {
		session.removeAttribute("loggedPartner");
		session.invalidate();
		return "redirect:/partner/login";
	}
}