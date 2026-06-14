package com.localservice.config;

import com.localservice.model.Admin;
import com.localservice.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createAdmin(AdminRepository repo){

        return args -> {

            if(repo.findByUsername("admin")==null){

                Admin admin = new Admin();

                admin.setUsername("admin");
                admin.setPassword("admin123");

                repo.save(admin);

            }

        };

    }
}