//package com.localservice.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.localservice.model.ServicePartner;
//import com.localservice.model.ServicePartnerRequest;
//import com.localservice.repository.ServicePartnerRepository;
//import com.localservice.repository.ServicePartnerRequestRepository;
//
//@Service
//public class AdminService {
//
//    @Autowired
//    private ServicePartnerRequestRepository requestRepo;
//
//    @Autowired
//    private ServicePartnerRepository partnerRepo;
//
//    public void approvePartner(Long id) {
//        // Safely check if request still exists before processing
//        if (!requestRepo.existsById(id)) {
//            return; // already processed, just redirect silently
//        }
//
//        ServicePartnerRequest request = requestRepo.findById(id).get();
//
//        ServicePartner partner = new ServicePartner();
//        partner.setName(request.getName());
//        partner.setEmail(request.getEmail());
//        partner.setPhone(request.getPhone());
//        partner.setServiceType(request.getServiceType());
//        partner.setExperience(request.getExperience());
//        partner.setPassword("123456");
//
//        partnerRepo.save(partner);
//        requestRepo.deleteById(id);
//    }
//
//    public void rejectPartner(Long id) {
//        // Safely check if request still exists before deleting
//        if (!requestRepo.existsById(id)) {
//            return; // already rejected/approved, just redirect silently
//        }
//        requestRepo.deleteById(id);
//    }
//}

package com.localservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localservice.model.ServicePartner;
import com.localservice.model.ServicePartnerRequest;
import com.localservice.repository.ServicePartnerRepository;
import com.localservice.repository.ServicePartnerRequestRepository;

@Service
public class AdminService {

	@Autowired
	private ServicePartnerRequestRepository requestRepo;

	@Autowired
	private ServicePartnerRepository partnerRepo;

	public void approvePartner(Long id) {
		// Safely check if request still exists before processing
		if (!requestRepo.existsById(id)) {
			return; // already processed, just redirect silently
		}

		ServicePartnerRequest request = requestRepo.findById(id).get();

		ServicePartner partner = new ServicePartner();
		partner.setName(request.getName());
		partner.setEmail(request.getEmail());
		partner.setPhone(request.getPhone());
		partner.setServiceType(request.getServiceType());
		partner.setExperience(request.getExperience());
//        partner.setPassword("123456");
		partner.setPassword(request.getPassword());
		partner.setCity(request.getCity()); // ← add this
		partnerRepo.save(partner);
		requestRepo.deleteById(id);
	}

	public void rejectPartner(Long id) {
		// Safely check if request still exists before deleting
		if (!requestRepo.existsById(id)) {
			return; // already rejected/approved, just redirect silently
		}
		requestRepo.deleteById(id);
	}
}