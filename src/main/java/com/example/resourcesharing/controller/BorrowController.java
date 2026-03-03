package com.example.resourcesharing.controller;

import com.example.resourcesharing.model.BorrowRequest;
import com.example.resourcesharing.service.BorrowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/request")
    public BorrowRequest requestBorrow(
            @RequestParam Long resourceId,
            @RequestParam Long borrowerId,
            @RequestParam LocalDate dueDate
    ) {
        return borrowService.requestBorrow(resourceId, borrowerId, dueDate);
    }

    @PutMapping("/approve")
    public BorrowRequest approveRequest(
            @RequestParam Long requestId,
            @RequestParam Long ownerId) {

        return borrowService.approveRequest(requestId, ownerId);
    }

    @PutMapping("/return")
    public BorrowRequest returnResource(@RequestParam Long requestId) {
        return borrowService.returnResource(requestId);
    }

    @PutMapping("/reject")
    public BorrowRequest rejectRequest(
            @RequestParam Long requestId,
            @RequestParam Long ownerId) {

        return borrowService.rejectRequest(requestId, ownerId);
    }

    @GetMapping("/my-requests")
    public List<BorrowRequest> getMyRequests(@RequestParam Long borrowerId) {
        return borrowService.getRequestsByBorrower(borrowerId);
    }

    @GetMapping("/owner-requests")
    public List<BorrowRequest> getRequestsForOwner(@RequestParam Long ownerId) {
        return borrowService.getRequestsForOwner(ownerId);
    }
}