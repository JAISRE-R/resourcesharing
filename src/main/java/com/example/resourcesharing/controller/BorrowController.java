package com.example.resourcesharing.controller;

import com.example.resourcesharing.model.BorrowRequest;
import com.example.resourcesharing.service.BorrowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/request")
    public BorrowRequest requestBorrow(
            @RequestParam Long resourceId,
            @RequestParam Long borrowerId,
            @RequestParam String dueDate
    ) {
        LocalDate date = LocalDate.parse(dueDate);
        return borrowService.requestBorrow(resourceId, borrowerId, date);
    }

    @PutMapping("/approve")
    public BorrowRequest approveRequest(@RequestParam Long requestId) {
        return borrowService.approveRequest(requestId);
    }

    @PutMapping("/return")
    public BorrowRequest returnResource(@RequestParam Long requestId) {
        return borrowService.returnResource(requestId);
    }
}