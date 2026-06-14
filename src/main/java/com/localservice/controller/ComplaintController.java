package com.localservice.controller;

import com.localservice.model.Complaint;
import com.localservice.model.User;
import com.localservice.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintRepository complaintRepository;

    // User submits complaint
    @PostMapping
    public ResponseEntity<?> submitComplaint(
            @RequestBody Complaint complaint,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            return ResponseEntity.status(401).body("Not logged in");
        }

        complaint.setUserEmail(user.getEmail());
        complaint.setUserName(user.getName());
        complaintRepository.save(complaint);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Complaint submitted successfully");
        return ResponseEntity.ok(response);
    }

    // Admin views all complaints
    @GetMapping("/all")
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAllByOrderByCreatedAtDesc();
    }

    // Admin updates complaint status
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(status);
        complaintRepository.save(complaint);
        return ResponseEntity.ok("Status updated");
    }
}