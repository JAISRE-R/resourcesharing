package com.example.resourcesharing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "borrow_requests")
public class BorrowRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @Setter
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @Setter
    private String status; // PENDING, APPROVED, REJECTED, RETURNED

    private LocalDate requestDate;
    @Setter
    private LocalDate dueDate;
    @Setter
    private LocalDate returnDate;

    // 🔹 Constructor
    public BorrowRequest() {
        this.status = "PENDING";
        this.requestDate = LocalDate.now();
    }

    // 🔹 Getters & Setters

}
