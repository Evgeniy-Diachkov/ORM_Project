package com.example.edu.controller;

import com.example.edu.domain.learning.Assignment;
import com.example.edu.domain.submission.Submission;
import com.example.edu.service.AssignmentService;
import com.example.edu.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final SubmissionService submissionService;

    public AssignmentController(AssignmentService assignmentService, SubmissionService submissionService) {
        this.assignmentService = assignmentService;
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Assignment> create(@RequestParam Long lessonId,
                                             @RequestParam String title,
                                             @RequestParam String description) {
        return ResponseEntity.ok(assignmentService.createAssignment(lessonId, title, description));
    }

    @PostMapping("/{assignmentId}/submit")
    public ResponseEntity<Submission> submit(@PathVariable Long assignmentId,
                                             @RequestParam Long studentId,
                                             @RequestParam String content) {
        return ResponseEntity.ok(submissionService.submit(assignmentId, studentId, content));
    }

    @PutMapping("/grade/{submissionId}")
    public ResponseEntity<Submission> grade(@PathVariable Long submissionId,
                                            @RequestParam int score,
                                            @RequestParam String feedback) {
        return ResponseEntity.ok(submissionService.grade(submissionId, score, feedback));
    }
}
