package com.example.edu.domain.submission;

import com.example.edu.domain.base.BaseEntity;
import com.example.edu.domain.learning.Assignment;
import com.example.edu.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "submissions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"assignment_id","student_id"}))
@Getter
@Setter
public class Submission extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    @JsonIgnore
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private User student;

    private Instant submittedAt = Instant.now();

    @Column(length = 10000)
    private String content;

    private Integer score;
    private String feedback;
}
