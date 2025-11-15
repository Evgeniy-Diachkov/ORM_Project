package com.example.edu.service;

import com.example.edu.domain.course.Course;
import com.example.edu.domain.enrollment.Enrollment;
import com.example.edu.domain.user.User;
import com.example.edu.repo.CourseRepository;
import com.example.edu.repo.EnrollmentRepository;
import com.example.edu.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollRepo;
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(EnrollmentRepository enrollRepo, UserRepository userRepo, CourseRepository courseRepo) {
        this.enrollRepo = enrollRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    @Transactional
    public Enrollment enroll(Long studentId, Long courseId) {
        User student = userRepo.findById(studentId).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();

        enrollRepo.findByStudentAndCourse(student, course)
                .ifPresent(e -> {
                    throw new IllegalStateException("Already enrolled");
                });

        Enrollment e = new Enrollment();
        e.setStudent(student);
        e.setCourse(course);
        return enrollRepo.save(e);
    }

    public List<Enrollment> getByStudent(Long studentId) {
        User s = userRepo.findById(studentId).orElseThrow();
        return enrollRepo.findByStudent(s);
    }

    public void unenroll(Long enrollmentId) {
        enrollRepo.deleteById(enrollmentId);
    }
}
