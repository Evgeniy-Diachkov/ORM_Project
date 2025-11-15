package com.example.edu.repo;

import com.example.edu.domain.quiz.AnswerOption;
import com.example.edu.domain.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
    List<AnswerOption> findByQuestion(Question question);
}
