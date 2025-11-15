package com.example.edu.domain.course;

import com.example.edu.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "course_schedules")
@Getter
@Setter
public class CourseSchedule extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    private LocalDate startDate;
    private LocalDate endDate;
    private String weekday;
}
