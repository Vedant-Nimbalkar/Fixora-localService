package com.localservice.controller;

import com.localservice.model.ServiceRequest;
import com.localservice.model.User;
import com.localservice.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    // User submits request
    @PostMapping
    public ResponseEntity<?> submitRequest(
            @RequestBody ServiceRequest request,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            return ResponseEntity.status(401).body("Not logged in");
        }

        request.setUserEmail(user.getEmail());
        request.setUserName(user.getName());
        serviceRequestRepository.save(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Service request submitted successfully");
        return ResponseEntity.ok(response);
    }

    // Admin views all requests
    @GetMapping("/all")
    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAllByOrderByCreatedAtDesc();
    }

    // Admin updates status
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        ServiceRequest request = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        serviceRequestRepository.save(request);
        return ResponseEntity.ok("Status updated");
    }
}