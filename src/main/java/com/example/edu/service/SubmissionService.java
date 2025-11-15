package com.example.edu.service;

import com.example.edu.domain.learning.Assignment;
import com.example.edu.domain.submission.Submission;
import com.example.edu.domain.user.User;
import com.example.edu.repo.AssignmentRepository;
import com.example.edu.repo.SubmissionRepository;
import com.example.edu.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepo;
    private final AssignmentRepository assignmentRepo;
    private final UserRepository userRepo;

    public SubmissionService(SubmissionRepository submissionRepo, AssignmentRepository assignmentRepo, UserRepository userRepo) {
        this.submissionRepo = submissionRepo;
        this.assignmentRepo = assignmentRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Submission submit(Long assignmentId, Long studentId, String content) {
        Assignment a = assignmentRepo.findById(assignmentId).orElseThrow();
        User student = userRepo.findById(studentId).orElseThrow();

        submissionRepo.findByAssignmentAndStudent(a, student)
                .ifPresent(s -> { throw new IllegalStateException("Already submitted"); });

        Submission s = new Submission();
        s.setAssignment(a);
        s.setStudent(student);
        s.setContent(content);
        s.setSubmittedAt(Instant.now());
        return submissionRepo.save(s);
    }

    @Transactional
    public Submission grade(Long submissionId, int score, String feedback) {
        Submission s = submissionRepo.findById(submissionId).orElseThrow();
        s.setScore(score);
        s.setFeedback(feedback);
        return submissionRepo.save(s);
    }
}
