package com.example.edu.controller;

import com.example.edu.domain.course.Course;
import com.example.edu.dto.CourseCreateDto;
import com.example.edu.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody CourseCreateDto dto) {
        return ResponseEntity.ok(
                service.createCourse(dto.title(), dto.description(), dto.categoryId(), dto.teacherId())
        );
    }

    @GetMapping
    public List<Course> all() {
        return service.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable Long id) {
        return service.getCourse(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCourse(id);
    }
}
