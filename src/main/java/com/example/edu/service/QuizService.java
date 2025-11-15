package com.example.edu.service;

import com.example.edu.domain.course.CourseModule;
import com.example.edu.domain.quiz.*;
import com.example.edu.domain.user.User;
import com.example.edu.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class QuizService {
    private final QuizRepository quizRepo;
    private final QuestionRepository questionRepo;
    private final AnswerOptionRepository optionRepo;
    private final QuizSubmissionRepository submissionRepo;
    private final ModuleRepository moduleRepo;
    private final UserRepository userRepo;

    public QuizService(QuizRepository quizRepo, QuestionRepository questionRepo,
                       AnswerOptionRepository optionRepo, QuizSubmissionRepository submissionRepo,
                       ModuleRepository moduleRepo, UserRepository userRepo) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
        this.optionRepo = optionRepo;
        this.submissionRepo = submissionRepo;
        this.moduleRepo = moduleRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Quiz createQuiz(Long moduleId, String title) {
        CourseModule m = moduleRepo.findById(moduleId).orElseThrow();
        Quiz q = new Quiz();
        q.setCourseModule(m);
        q.setTitle(title);
        return quizRepo.save(q);
    }

    @Transactional
    public Question addQuestion(Long quizId, String text, String type) {
        Quiz q = quizRepo.findById(quizId).orElseThrow();
        Question question = new Question();
        question.setQuiz(q);
        question.setText(text);
        question.setType(type);
        return questionRepo.save(question);
    }

    @Transactional
    public AnswerOption addOption(Long questionId, String text, boolean isCorrect) {
        Question question = questionRepo.findById(questionId).orElseThrow();
        AnswerOption opt = new AnswerOption();
        opt.setQuestion(question);
        opt.setText(text);
        opt.setCorrect(isCorrect);
        return optionRepo.save(opt);
    }

    @Transactional
    public QuizSubmission submitQuiz(Long quizId, Long studentId, int score) {
        Quiz quiz = quizRepo.findById(quizId).orElseThrow();
        User student = userRepo.findById(studentId).orElseThrow();

        submissionRepo.findByQuizAndStudent(quiz, student)
                .ifPresent(s -> { throw new IllegalStateException("Already taken"); });

        QuizSubmission s = new QuizSubmission();
        s.setQuiz(quiz);
        s.setStudent(student);
        s.setScore(score);
        s.setTakenAt(Instant.now());
        return submissionRepo.save(s);
    }
}
