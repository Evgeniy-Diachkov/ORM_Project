package com.example.edu.repo;

import com.example.edu.domain.quiz.Quiz;
import com.example.edu.domain.course.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByCourseModule(CourseModule courseModule);
}
