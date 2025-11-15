package com.example.edu.repo;

import com.example.edu.domain.submission.Submission;
import com.example.edu.domain.learning.Assignment;
import com.example.edu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignment(Assignment assignment);
    List<Submission> findByStudent(User student);
    Optional<Submission> findByAssignmentAndStudent(Assignment assignment, User student);
}
