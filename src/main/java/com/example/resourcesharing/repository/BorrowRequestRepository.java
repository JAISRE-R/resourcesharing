package com.example.resourcesharing.repository;

import com.example.resourcesharing.model.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long> {


    List<BorrowRequest> findByBorrower_Id(Long borrowerId);

    List<BorrowRequest> findByResource_Owner_Id(Long ownerId);

    List<BorrowRequest> findByResource_IdAndBorrower_Id(Long resourceId, Long borrowerId);

    boolean existsByResource_IdAndStatus(Long resourceId, String status);

    List<BorrowRequest> findByResource_IdAndStatus(Long resourceId, String status);
}