package com.swiftmart.swiftmart_backend.repository;

import com.swiftmart.swiftmart_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}