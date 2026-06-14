//package com.localservice.repository;
//
//import com.localservice.model.ServicePartner;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import java.util.List;
//
//public interface ServicePartnerRepository extends JpaRepository<ServicePartner, Long> {
//
//    @Query("SELECT p FROM ServicePartner p WHERE p.suspended = false")
//    List<ServicePartner> findActivePartners();
//
//    @Query("SELECT p FROM ServicePartner p")
//    List<ServicePartner> findAllPartners();
//
//    ServicePartner findByEmail(String email);
//
//    @Query("SELECT p FROM ServicePartner p WHERE p.suspended = false AND LOWER(p.city) = LOWER(:city)")
//    List<ServicePartner> findActivePartnersByCity(@Param("city") String city);
//}

package com.localservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.localservice.model.ServicePartner;

public interface ServicePartnerRepository extends JpaRepository<ServicePartner, Long> {

    // Active partners only (not suspended)
    @Query("SELECT p FROM ServicePartner p WHERE p.suspended = false")
    List<ServicePartner> findActivePartners();

    // All partners (for admin)
    @Query("SELECT p FROM ServicePartner p")
    List<ServicePartner> findAllPartners();

    ServicePartner findByEmail(String email);
    
    @Query("SELECT p FROM ServicePartner p WHERE p.suspended = false AND LOWER(p.city) = LOWER(:city)")
    List<ServicePartner> findActivePartnersByCity(@Param("city") String city);
}