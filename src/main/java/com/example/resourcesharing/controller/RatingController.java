package com.example.resourcesharing.controller;

import com.example.resourcesharing.model.Rating;
import com.example.resourcesharing.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/rate") // temporary GET for browser testing
    public Rating rateUser(
            @RequestParam Long requestId,
            @RequestParam Long raterId,
            @RequestParam Long ratedUserId,
            @RequestParam int score,
            @RequestParam String comment
    ) {
        return ratingService.rateUser(requestId, raterId, ratedUserId, score, comment);
    }
}