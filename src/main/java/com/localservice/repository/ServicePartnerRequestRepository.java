package com.localservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localservice.model.ServicePartnerRequest;


@Repository
public interface ServicePartnerRequestRepository 
      extends JpaRepository<ServicePartnerRequest, Long> {
}