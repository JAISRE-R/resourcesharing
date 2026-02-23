package com.example.resourcesharing.model;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private int score; // 1 to 5

    @Setter
    private String comment;

    @Setter
    @ManyToOne
    @JoinColumn(name = "rater_id")
    private User rater;

    @Setter
    @ManyToOne
    @JoinColumn(name = "rated_user_id")
    private User ratedUser;

    @Setter
    @ManyToOne
    @JoinColumn(name = "borrow_request_id")
    private BorrowRequest borrowRequest;


    // (Optional but good) getters

    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public User getRater() {
        return rater;
    }

    public User getRatedUser() {
        return ratedUser;
    }

    public BorrowRequest getBorrowRequest() {
        return borrowRequest;
    }
}