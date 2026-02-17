package com.example.resourcesharing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String description;

    @Setter
    private String category;

    @Setter
    @Column(name = "availability_status")
    private String availabilityStatus;

    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // 🔹 Constructors
    public Resource() {
        this.availabilityStatus = "AVAILABLE"; // default value
    }

    // 🔹 Getters and Setters

}