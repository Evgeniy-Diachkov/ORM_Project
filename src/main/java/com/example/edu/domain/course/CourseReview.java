package com.example.edu.domain.course;

import com.example.edu.domain.base.BaseEntity;
import com.example.edu.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "course_reviews")
@Getter
@Setter
public class CourseReview extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private User student;

    @Column(nullable = false)
    private Integer rating; // 1..5

    @Column(length = 4000)
    private String comment;

    private Instant createdAtReview = Instant.now();

}
