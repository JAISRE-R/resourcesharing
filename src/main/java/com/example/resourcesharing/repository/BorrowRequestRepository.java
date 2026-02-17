package com.example.resourcesharing.repository;

import com.example.resourcesharing.model.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long> {


}