package com.example.edu.controller;

import com.example.edu.domain.submission.Submission;
import com.example.edu.service.SubmissionService;
import com.example.edu.repo.SubmissionRepository;
import com.example.edu.domain.learning.Assignment;
import com.example.edu.domain.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final SubmissionRepository submissionRepo;

    public SubmissionController(SubmissionService submissionService,
                                SubmissionRepository submissionRepo) {
        this.submissionService = submissionService;
        this.submissionRepo = submissionRepo;
    }

    // 📤 Отправить решение на задание
    @PostMapping
    public ResponseEntity<Submission> submit(@RequestParam Long assignmentId,
                                             @RequestParam Long studentId,
                                             @RequestParam String content) {
        Submission s = submissionService.submit(assignmentId, studentId, content);
        return ResponseEntity.ok(s);
    }

    // 🧾 Просмотреть решение по id
    @GetMapping("/{id}")
    public ResponseEntity<Submission> getById(@PathVariable Long id) {
        return submissionRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 📋 Получить все решения по конкретному заданию
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<Submission>> getByAssignment(@PathVariable Long assignmentId) {
        Assignment a = new Assignment();
        a.setId(assignmentId);
        return ResponseEntity.ok(submissionRepo.findByAssignment(a));
    }

    // 📚 Получить все решения конкретного студента
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Submission>> getByStudent(@PathVariable Long studentId) {
        User u = new User();
        u.setId(studentId);
        return ResponseEntity.ok(submissionRepo.findByStudent(u));
    }

    // 🧮 Оценить решение
    @PutMapping("/{id}/grade")
    public ResponseEntity<Submission> grade(@PathVariable Long id,
                                            @RequestParam int score,
                                            @RequestParam(required = false) String feedback) {
        Submission updated = submissionService.grade(id, score, feedback);
        return ResponseEntity.ok(updated);
    }

    // ❌ Удалить решение (например, если тестовое или ошибочное)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (submissionRepo.existsById(id)) {
            submissionRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
