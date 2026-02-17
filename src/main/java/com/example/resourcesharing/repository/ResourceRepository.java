package com.example.resourcesharing.repository;

import com.example.resourcesharing.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
