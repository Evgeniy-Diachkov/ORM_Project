package com.example.edu.controller;

import com.example.edu.domain.enrollment.Enrollment;
import com.example.edu.dto.EnrollmentRequestDto;
import com.example.edu.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@Valid @RequestBody EnrollmentRequestDto dto) {
        return ResponseEntity.ok(service.enroll(dto.studentId(), dto.courseId()));
    }


    @GetMapping("/student/{id}")
    public List<Enrollment> getByStudent(@PathVariable Long id) {
        return service.getByStudent(id);
    }

    @DeleteMapping("/{id}")
    public void unenroll(@PathVariable Long id) { service.unenroll(id); }
}
