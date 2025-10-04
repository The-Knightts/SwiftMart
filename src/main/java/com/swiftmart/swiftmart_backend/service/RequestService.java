package com.swiftmart.swiftmart_backend.service;

import com.swiftmart.swiftmart_backend.model.Request;
import com.swiftmart.swiftmart_backend.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    
    @Autowired
    private RequestRepository requestRepository;
    
    // Shopkeeper methods
    public Request createRequest(Request request) {
        request.setStatus("PENDING");
        request.setCreatedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }
    
    public List<Request> getRequestsByShopkeeper(String shopkeeperEmail) {
        return requestRepository.findByShopkeeperEmailOrderByCreatedAtDesc(shopkeeperEmail);
    }
    
    public List<Request> getRequestsByShopkeeperAndStatus(String shopkeeperEmail, String status) {
        return requestRepository.findByShopkeeperEmailAndStatusOrderByCreatedAtDesc(shopkeeperEmail, status);
    }
    
    // Vendor methods
    public List<Request> getAllPendingRequests() {
        return requestRepository.findByStatusOrderByCreatedAtDesc("PENDING");
    }
    
    public List<Request> getRequestsByVendor(String vendorName) {
        return requestRepository.findByVendorNameOrderByCreatedAtDesc(vendorName);
    }
    
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
    
    public Request updateRequestStatus(Long requestId, String status, String vendorName, String vendorResponse) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            request.setStatus(status);
            request.setVendorName(vendorName);
            request.setVendorResponse(vendorResponse);
            request.setUpdatedAt(LocalDateTime.now());
            return requestRepository.save(request);
        }
        return null;
    }
    
    public Request approveRequest(Long requestId, String vendorName) {
        return updateRequestStatus(requestId, "APPROVED", vendorName, "Request approved by vendor");
    }
    
    public Request rejectRequest(Long requestId, String vendorName, String reason) {
        return updateRequestStatus(requestId, "REJECTED", vendorName, reason);
    }
    
    public Request completeRequest(Long requestId, String vendorName) {
        return updateRequestStatus(requestId, "COMPLETED", vendorName, "Request completed");
    }
    
    // Search and statistics
    public List<Request> searchRequests(String keyword) {
        return requestRepository.searchRequests(keyword);
    }
    
    public Long getTotalRequestsCount() {
        return requestRepository.countAllRequests();
    }
    
    public Long getRequestsCountByStatus(String status) {
        return requestRepository.countByStatus(status);
    }
    
    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }
    
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}