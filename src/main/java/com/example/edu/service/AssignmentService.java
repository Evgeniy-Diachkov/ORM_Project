package com.example.edu.service;

import com.example.edu.domain.learning.Assignment;
import com.example.edu.domain.course.Lesson;
import com.example.edu.repo.AssignmentRepository;
import com.example.edu.repo.LessonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepo;
    private final LessonRepository lessonRepo;

    public AssignmentService(AssignmentRepository assignmentRepo, LessonRepository lessonRepo) {
        this.assignmentRepo = assignmentRepo;
        this.lessonRepo = lessonRepo;
    }

    @Transactional
    public Assignment createAssignment(Long lessonId, String title, String description) {
        Lesson lesson = lessonRepo.findById(lessonId).orElseThrow();
        Assignment a = new Assignment();
        a.setLesson(lesson);
        a.setTitle(title);
        a.setDescription(description);
        a.setDueDate(Instant.now().plusSeconds(7 * 24 * 3600)); // неделя
        return assignmentRepo.save(a);
    }
}
