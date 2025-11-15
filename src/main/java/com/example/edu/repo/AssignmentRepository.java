package com.example.edu.repo;

import com.example.edu.domain.learning.Assignment;
import com.example.edu.domain.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByLesson(Lesson lesson);
}
