package com.example.resourcesharing.service;

import com.example.resourcesharing.model.*;
import com.example.resourcesharing.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BorrowRequestRepository borrowRequestRepository;

    @Autowired
    private UserRepository userRepository;

    public Rating rateUser(Long requestId, Long raterId, Long ratedUserId, int score, String comment) {

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        User rater = userRepository.findById(raterId)
                .orElseThrow(() -> new RuntimeException("Rater not found"));

        User ratedUser = userRepository.findById(ratedUserId)
                .orElseThrow(() -> new RuntimeException("Rated user not found"));

        if (!request.getStatus().equals("RETURNED")) {
            throw new RuntimeException("You can rate only after return");
        }

        Rating rating = new Rating();
        rating.setBorrowRequest(request);
        rating.setRater(rater);
        rating.setRatedUser(ratedUser);
        rating.setScore(score);
        rating.setComment(comment);

        return ratingRepository.save(rating);
    }
}
