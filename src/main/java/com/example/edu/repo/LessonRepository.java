package com.example.edu.repo;

import com.example.edu.domain.course.Lesson;
import com.example.edu.domain.course.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseModule(CourseModule courseModule);
}
