//package com.localservice.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.localservice.repository.ServicePartnerRepository;
//import com.localservice.repository.ServicePartnerRequestRepository;
//import com.localservice.service.AdminService;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private ServicePartnerRequestRepository requestRepo;
//
//    @Autowired
//    private ServicePartnerRepository partnerRepo;
//
//    @Autowired
//    private AdminService adminService;
//
//    @GetMapping("/dashboard")
//    public String dashboard(Model model) {
//        model.addAttribute("requests", requestRepo.findAll());
//        model.addAttribute("partners", partnerRepo.findAll());
//
//        long totalRequests = requestRepo.count();
//        long pending = requestRepo.findAll().stream()
//                        .filter(r -> r.getStatus().equals("PENDING")).count();
//        long approved = partnerRepo.count();
//        long rejected = totalRequests - pending - approved;
//
//        model.addAttribute("totalRequests", totalRequests);
//        model.addAttribute("pendingCount", pending);
//        model.addAttribute("approvedCount", approved);
//        model.addAttribute("rejectedCount", rejected);
//
//        return "admin-dashboard";
//    }
//
//    // ✅ THIS WAS MISSING — add these two back
//    @GetMapping("/approve/{id}")
//    public String approvePartner(@PathVariable Long id) {
//        adminService.approvePartner(id);
//        return "redirect:/admin/dashboard";
//    }
//
//    @GetMapping("/reject/{id}")
//    public String reject(@PathVariable Long id) {
//        adminService.rejectPartner(id);
//        return "redirect:/admin/dashboard";
//    }
//}
package com.localservice.controller;

import com.localservice.model.Complaint;
import com.localservice.model.ServiceRequest;
import com.localservice.repository.ComplaintRepository;
import com.localservice.repository.ServiceRequestRepository;
import com.localservice.repository.ServicePartnerRepository;
import com.localservice.repository.ServicePartnerRequestRepository;
import com.localservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ServicePartnerRequestRepository requestRepo;

    @Autowired
    private ServicePartnerRepository partnerRepo;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("requests", requestRepo.findAll());
        model.addAttribute("partners", partnerRepo.findAllPartners());

        // Stats
        long totalRequests = requestRepo.count();
        long pending = requestRepo.findAll().stream()
                .filter(r -> r.getStatus().equals("PENDING")).count();
        long approved = partnerRepo.findAllPartners().size();
        long rejected = totalRequests - pending - approved;

        model.addAttribute("totalRequests", totalRequests);
        model.addAttribute("pendingCount", pending);
        model.addAttribute("approvedCount", approved);
        model.addAttribute("rejectedCount", rejected);

        // New sections
        model.addAttribute("complaints",
            complaintRepository.findAllByOrderByCreatedAtDesc());
        model.addAttribute("serviceRequests",
            serviceRequestRepository.findAllByOrderByCreatedAtDesc());

        return "admin-dashboard";
    }

    // Approve partner request
    @GetMapping("/approve/{id}")
    public String approvePartner(@PathVariable Long id) {
        adminService.approvePartner(id);
        return "redirect:/admin/dashboard";
    }

    // Reject partner request
    @GetMapping("/reject/{id}")
    public String rejectPartner(@PathVariable Long id) {
        adminService.rejectPartner(id);
        return "redirect:/admin/dashboard";
    }

    // Suspend partner
    @GetMapping("/suspend/{id}")
    public String suspendPartner(@PathVariable Long id) {
        partnerRepo.findById(id).ifPresent(partner -> {
            partner.setSuspended(true);
            partnerRepo.save(partner);
        });
        return "redirect:/admin/dashboard";
    }

    // Unsuspend partner
    @GetMapping("/unsuspend/{id}")
    public String unsuspendPartner(@PathVariable Long id) {
        partnerRepo.findById(id).ifPresent(partner -> {
            partner.setSuspended(false);
            partnerRepo.save(partner);
        });
        return "redirect:/admin/dashboard";
    }

    // Delete partner
    @GetMapping("/delete/{id}")
    public String deletePartner(@PathVariable Long id) {
        partnerRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    // Update complaint status
    @GetMapping("/complaint/{id}/resolve")
    public String resolveComplaint(@PathVariable Long id) {
        complaintRepository.findById(id).ifPresent(c -> {
            c.setStatus("RESOLVED");
            complaintRepository.save(c);
        });
        return "redirect:/admin/dashboard";
    }

    // Update service request status
    @GetMapping("/service-request/{id}/fulfil")
    public String fulfilServiceRequest(@PathVariable Long id) {
        serviceRequestRepository.findById(id).ifPresent(r -> {
            r.setStatus("FULFILLED");
            serviceRequestRepository.save(r);
        });
        return "redirect:/admin/dashboard";
    }
}