package com.swiftmart.swiftmart_backend.config;

import com.swiftmart.swiftmart_backend.model.User;
import com.swiftmart.swiftmart_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create test users if they don't exist
        
        // Test Shopkeeper
        if (!userRepository.existsByUsername("shopkeeper1")) {
            User shopkeeper = new User();
            shopkeeper.setUsername("shopkeeper1");
            shopkeeper.setEmail("shopkeeper1@swiftmart.com");
            shopkeeper.setPassword(passwordEncoder.encode("password123"));
            shopkeeper.setRole("SHOPKEEPER");
            shopkeeper.setShopId("SHOP001");
            userRepository.save(shopkeeper);
            System.out.println("Created test shopkeeper user: shopkeeper1");
        }

        // Test Vendor
        if (!userRepository.existsByUsername("vendor1")) {
            User vendor = new User();
            vendor.setUsername("vendor1");
            vendor.setEmail("vendor1@swiftmart.com");
            vendor.setPassword(passwordEncoder.encode("password123"));
            vendor.setRole("VENDOR");
            vendor.setShopId("VENDOR001");
            userRepository.save(vendor);
            System.out.println("Created test vendor user: vendor1");
        }

        // Test Admin
        if (!userRepository.existsByUsername("admin1")) {
            User admin = new User();
            admin.setUsername("admin1");
            admin.setEmail("admin1@swiftmart.com");
            admin.setPassword(passwordEncoder.encode("password123"));
            admin.setRole("ADMIN");
            admin.setShopId("ADMIN001");
            userRepository.save(admin);
            System.out.println("Created test admin user: admin1");
        }
    }
}