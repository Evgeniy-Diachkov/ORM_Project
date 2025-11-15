package com.example.edu.it;

import com.example.edu.domain.course.*;
import com.example.edu.domain.learning.Assignment;
import com.example.edu.domain.submission.Submission;
import com.example.edu.domain.user.User;
import com.example.edu.domain.user.UserRole;
import com.example.edu.repo.*;
import com.example.edu.service.AssignmentService;
import com.example.edu.service.SubmissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubmissionIT extends PostgresContainerBase {

    @Autowired
    UserRepository userRepo;
    @Autowired
    CategoryRepository catRepo;
    @Autowired
    CourseRepository courseRepo;
    @Autowired
    ModuleRepository moduleRepo;
    @Autowired
    LessonRepository lessonRepo;
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    SubmissionService submissionService;

    @Test
    void submit_onceAndGrade() {
        User st = new User();
        st.setName("S");
        st.setEmail("s@e.com");
        st.setRole(UserRole.STUDENT);
        userRepo.save(st);
        User te = new User();
        te.setName("T");
        te.setEmail("t@e.com");
        te.setRole(UserRole.TEACHER);
        userRepo.save(te);

        Category cat = new Category();
        cat.setName("Prog");
        catRepo.save(cat);
        Course c = new Course();
        c.setTitle("Java");
        c.setDescription("Intro");
        c.setCategory(cat);
        c.setTeacher(te);
        courseRepo.save(c);
        CourseModule m = new CourseModule();
        m.setCourse(c);
        m.setTitle("M1");
        m.setOrderIndex(1);
        moduleRepo.save(m);
        Lesson l = new Lesson();
        l.setCourseModule(m);
        l.setTitle("L1");
        l.setContent("...");
        lessonRepo.save(l);

        Assignment a = assignmentService.createAssignment(l.getId(), "HW1", "Do it");

        Submission s1 = submissionService.submit(a.getId(), st.getId(), "answer");
        assertNotNull(s1.getId());

        IllegalStateException dup = assertThrows(IllegalStateException.class,
                () -> submissionService.submit(a.getId(), st.getId(), "again"));
        assertTrue(dup.getMessage().toLowerCase().contains("already"));

        Submission graded = submissionService.grade(s1.getId(), 95, "good");
        assertEquals(95, graded.getScore());
        assertEquals("good", graded.getFeedback());
    }
}
