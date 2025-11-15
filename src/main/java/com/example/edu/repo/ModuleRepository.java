package com.example.edu.repo;

import com.example.edu.domain.course.Course;
import com.example.edu.domain.course.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<CourseModule, Long> {
    List<CourseModule> findByCourse(Course course);
}

