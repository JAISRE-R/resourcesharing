package com.example.resourcesharing.service;

import com.example.resourcesharing.model.*;
import com.example.resourcesharing.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRequestRepository borrowRequestRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserRepository userRepository;

    public BorrowRequest requestBorrow(Long resourceId, Long borrowerId, LocalDate dueDate) {

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        User borrower = userRepository.findById(borrowerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // block if already borrowed by someone
        boolean alreadyBorrowed =
                borrowRequestRepository.existsByResource_IdAndStatus(resourceId, "APPROVED");

        if (alreadyBorrowed) {
            throw new RuntimeException("Resource is already borrowed by another user");
        }

        List<BorrowRequest> existingRequests =
                borrowRequestRepository.findByResource_IdAndBorrower_Id(resourceId, borrowerId);

        for (BorrowRequest req : existingRequests) {
            if ("PENDING".equals(req.getStatus()) || "APPROVED".equals(req.getStatus())) {
                throw new RuntimeException("You already have an active request for this resource");
            }
        }


        if (!resource.getAvailabilityStatus().equals("AVAILABLE")) {
            throw new RuntimeException("Resource is not available");
        }

        BorrowRequest request = new BorrowRequest();
        request.setResource(resource);
        request.setBorrower(borrower);
        request.setDueDate(dueDate);
        request.setStatus("PENDING");

        return borrowRequestRepository.save(request);
    }


    public BorrowRequest approveRequest(Long requestId) {

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Resource resource = request.getResource();

        if (!resource.getAvailabilityStatus().equals("AVAILABLE")) {
            throw new RuntimeException("Resource already borrowed");
        }

        request.setStatus("APPROVED");
        resource.setAvailabilityStatus("BORROWED");

        // reject other pending requests for same resource
        List<BorrowRequest> pendingRequests =
                borrowRequestRepository.findByResource_IdAndStatus(resource.getId(), "PENDING");

        for (BorrowRequest r : pendingRequests) {
            if (!r.getId().equals(requestId)) {
                r.setStatus("REJECTED");
                borrowRequestRepository.save(r);
            }
        }

        resourceRepository.save(resource);

        return borrowRequestRepository.save(request);
    }public BorrowRequest returnResource(Long requestId) {

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getStatus().equals("APPROVED")) {
            throw new RuntimeException("Only approved requests can be returned");
        }

        Resource resource = request.getResource();

        request.setStatus("RETURNED");
        request.setReturnDate(LocalDate.now());

        resource.setAvailabilityStatus("AVAILABLE");

        resourceRepository.save(resource);

        return borrowRequestRepository.save(request);
    }
    public List<BorrowRequest> getRequestsByBorrower(Long borrowerId) {
        return borrowRequestRepository.findByBorrower_Id(borrowerId);
    }

    public List<BorrowRequest> getRequestsForOwner(Long ownerId) {
        return borrowRequestRepository.findByResource_Owner_Id(ownerId);
    }



}