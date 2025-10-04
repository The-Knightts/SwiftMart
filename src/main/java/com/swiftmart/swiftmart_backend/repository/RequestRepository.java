package com.swiftmart.swiftmart_backend.repository;

import com.swiftmart.swiftmart_backend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    
    // Find requests by shopkeeper email
    List<Request> findByShopkeeperEmailOrderByCreatedAtDesc(String shopkeeperEmail);
    
    // Find requests by status
    List<Request> findByStatusOrderByCreatedAtDesc(String status);
    
    // Find requests by vendor name
    List<Request> findByVendorNameOrderByCreatedAtDesc(String vendorName);
    
    // Find requests by shopkeeper email and status
    List<Request> findByShopkeeperEmailAndStatusOrderByCreatedAtDesc(String shopkeeperEmail, String status);
    
    // Custom query to search requests
    @Query("SELECT r FROM Request r WHERE " +
           "r.shopkeeperName LIKE %:keyword% OR " +
           "r.productName LIKE %:keyword% OR " +
           "r.category LIKE %:keyword%")
    List<Request> searchRequests(@Param("keyword") String keyword);
    
    // Count requests by status
    @Query("SELECT COUNT(r) FROM Request r WHERE r.status = :status")
    Long countByStatus(@Param("status") String status);
    
    // Count all requests
    @Query("SELECT COUNT(r) FROM Request r")
    Long countAllRequests();
}