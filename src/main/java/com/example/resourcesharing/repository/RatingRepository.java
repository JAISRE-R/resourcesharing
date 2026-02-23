package com.example.resourcesharing.repository;

import com.example.resourcesharing.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
