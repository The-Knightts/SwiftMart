package com.swiftmart.swiftmart_backend.controller;

import com.swiftmart.swiftmart_backend.model.Request;
import com.swiftmart.swiftmart_backend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*")
public class RequestController {
    
    @Autowired
    private RequestService requestService;
    
    // Shopkeeper endpoints
    
    @PostMapping("/shopkeeper/create")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request createdRequest = requestService.createRequest(request);
        return ResponseEntity.ok(createdRequest);
    }
    
    @GetMapping("/shopkeeper/{email}")
    public ResponseEntity<List<Request>> getRequestsByShopkeeper(@PathVariable String email) {
        List<Request> requests = requestService.getRequestsByShopkeeper(email);
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/shopkeeper/{email}/status/{status}")
    public ResponseEntity<List<Request>> getRequestsByShopkeeperAndStatus(
            @PathVariable String email, 
            @PathVariable String status) {
        List<Request> requests = requestService.getRequestsByShopkeeperAndStatus(email, status);
        return ResponseEntity.ok(requests);
    }
    
    // Vendor endpoints
    
    @GetMapping("/vendor/pending")
    public ResponseEntity<List<Request>> getAllPendingRequests() {
        List<Request> requests = requestService.getAllPendingRequests();
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/vendor/{vendorName}")
    public ResponseEntity<List<Request>> getRequestsByVendor(@PathVariable String vendorName) {
        List<Request> requests = requestService.getRequestsByVendor(vendorName);
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/vendor/all")
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }
    
    @PutMapping("/vendor/approve/{requestId}")
    public ResponseEntity<Request> approveRequest(
            @PathVariable Long requestId,
            @RequestParam String vendorName) {
        Request updatedRequest = requestService.approveRequest(requestId, vendorName);
        if (updatedRequest != null) {
            return ResponseEntity.ok(updatedRequest);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/vendor/reject/{requestId}")
    public ResponseEntity<Request> rejectRequest(
            @PathVariable Long requestId,
            @RequestParam String vendorName,
            @RequestParam String reason) {
        Request updatedRequest = requestService.rejectRequest(requestId, vendorName, reason);
        if (updatedRequest != null) {
            return ResponseEntity.ok(updatedRequest);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/vendor/complete/{requestId}")
    public ResponseEntity<Request> completeRequest(
            @PathVariable Long requestId,
            @RequestParam String vendorName) {
        Request updatedRequest = requestService.completeRequest(requestId, vendorName);
        if (updatedRequest != null) {
            return ResponseEntity.ok(updatedRequest);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Common endpoints
    
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Optional<Request> request = requestService.getRequestById(id);
        return request.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Request>> searchRequests(@RequestParam String keyword) {
        List<Request> requests = requestService.searchRequests(keyword);
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/stats/count")
    public ResponseEntity<Long> getTotalRequestsCount() {
        Long count = requestService.getTotalRequestsCount();
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/stats/count/{status}")
    public ResponseEntity<Long> getRequestsCountByStatus(@PathVariable String status) {
        Long count = requestService.getRequestsCountByStatus(status);
        return ResponseEntity.ok(count);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}