package com.example.edu.repo;

import com.example.edu.domain.course.Category;
import com.example.edu.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}

