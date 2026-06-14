package com.localservice.repository;

import com.localservice.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long>{

    Admin findByUsername(String username);

}