package com.example.resourcesharing.repository;

import com.example.resourcesharing.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findByAvailabilityStatus(String availabilityStatus);

    List<Resource> findByOwner_Id(Long ownerId);
}