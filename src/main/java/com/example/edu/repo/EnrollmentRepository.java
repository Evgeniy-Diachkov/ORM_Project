package com.example.edu.repo;

import com.example.edu.domain.enrollment.Enrollment;
import com.example.edu.domain.course.Course;
import com.example.edu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(User student);
    List<Enrollment> findByCourse(Course course);
    Optional<Enrollment> findByStudentAndCourse(User student, Course course);
}
