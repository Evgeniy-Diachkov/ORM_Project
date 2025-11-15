package com.example.edu.repo;

import com.example.edu.domain.quiz.QuizSubmission;
import com.example.edu.domain.quiz.Quiz;
import com.example.edu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
    List<QuizSubmission> findByStudent(User student);
    List<QuizSubmission> findByQuiz(Quiz quiz);
    Optional<QuizSubmission> findByQuizAndStudent(Quiz quiz, User student);
}
