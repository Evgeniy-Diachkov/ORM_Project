package com.example.edu.repo;

import com.example.edu.domain.course.Course;
import com.example.edu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
    List<Course> findByCategory_Name(String categoryName);
}

