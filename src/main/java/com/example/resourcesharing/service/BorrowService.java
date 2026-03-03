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

        if (resource.getOwner().getId().equals(borrowerId)) {
            throw new RuntimeException("You cannot borrow your own resource");
        }

        // block if already borrowed by someone
        boolean alreadyBorrowed =
                borrowRequestRepository.existsByResource_IdAndStatus(
                        resourceId,
                        RequestStatus.APPROVED
                );

        if (alreadyBorrowed) {
            throw new RuntimeException("Resource is already borrowed by another user");
        }

        List<BorrowRequest> existingRequests =
                borrowRequestRepository.findByResource_IdAndBorrower_Id(resourceId, borrowerId);

        for (BorrowRequest req : existingRequests) {
            if (req.getStatus() == RequestStatus.PENDING ||
                    req.getStatus() == RequestStatus.APPROVED) {
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
        request.setStatus(RequestStatus.PENDING);

        return borrowRequestRepository.save(request);
    }


    public BorrowRequest approveRequest(Long requestId, Long ownerId) {

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Resource resource = request.getResource();

        if (!resource.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Only owner can approve this request");
        }

        if (!resource.getAvailabilityStatus().equals("AVAILABLE")) {
            throw new RuntimeException("Resource already borrowed");
        }

        request.setStatus(RequestStatus.APPROVED);
        resource.setAvailabilityStatus("BORROWED");

        // reject other pending requests for same resource
        List<BorrowRequest> pendingRequests =
                borrowRequestRepository.findByResource_IdAndStatus(
                        resource.getId(),
                        RequestStatus.PENDING
                );

        for (BorrowRequest r : pendingRequests) {
            if (!r.getId().equals(requestId)) {
                request.setStatus(RequestStatus.REJECTED);
                borrowRequestRepository.save(r);
            }
        }

        resourceRepository.save(resource);

        return borrowRequestRepository.save(request);
    }public BorrowRequest returnResource(Long requestId) {

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RequestStatus.APPROVED) {
            throw new RuntimeException("Only approved requests can be returned");
        }

        Resource resource = request.getResource();

        request.setStatus(RequestStatus.RETURNED);
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

    public BorrowRequest rejectRequest(Long requestId, Long ownerId) {

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Resource resource = request.getResource();

        if (!resource.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Only owner can reject");
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Only pending requests can be rejected");
        }

        request.setStatus(RequestStatus.REJECTED);

        return borrowRequestRepository.save(request);
    }



}