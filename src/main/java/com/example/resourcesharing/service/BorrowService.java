package com.example.resourcesharing.service;

import com.example.resourcesharing.model.*;
import com.example.resourcesharing.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        if (!resource.getAvailabilityStatus().equals("AVAILABLE")) {
            throw new RuntimeException("Resource is not available");
        }

        BorrowRequest request = new BorrowRequest();
        request.setResource(resource);
        request.setBorrower(borrower);
        request.setDueDate(dueDate);

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



}