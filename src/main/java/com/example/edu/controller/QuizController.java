package com.example.edu.controller;

import com.example.edu.domain.quiz.*;
import com.example.edu.dto.AnswerOptionCreateDto;
import com.example.edu.dto.QuestionCreateDto;
import com.example.edu.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService service;

    public QuizController(QuizService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestParam Long moduleId,
                                           @RequestParam String title) {
        return ResponseEntity.ok(service.createQuiz(moduleId, title));
    }

    @PostMapping("/question")
    public ResponseEntity<Question> addQuestion(@Valid @RequestBody QuestionCreateDto dto) {
        return ResponseEntity.ok(service.addQuestion(dto.quizId(), dto.text(), dto.type()));
    }

    @PostMapping("/option")
    public ResponseEntity<AnswerOption> addOption(@Valid @RequestBody AnswerOptionCreateDto dto) {
        return ResponseEntity.ok(service.addOption(dto.questionId(), dto.text(), dto.correct()));
    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<QuizSubmission> submit(@PathVariable Long quizId,
                                                 @RequestParam Long studentId,
                                                 @RequestParam int score) {
        return ResponseEntity.ok(service.submitQuiz(quizId, studentId, score));
    }
}
