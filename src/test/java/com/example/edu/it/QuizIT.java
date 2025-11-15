package com.example.edu.it;

import com.example.edu.domain.course.*;
import com.example.edu.domain.quiz.*;
import com.example.edu.domain.user.User;
import com.example.edu.domain.user.UserRole;
import com.example.edu.repo.*;
import com.example.edu.service.QuizService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuizIT extends PostgresContainerBase {

    @Autowired
    UserRepository userRepo;
    @Autowired
    CategoryRepository catRepo;
    @Autowired
    CourseRepository courseRepo;
    @Autowired
    ModuleRepository moduleRepo;
    @Autowired
    QuizService quizService;
    @Autowired
    QuizSubmissionRepository qsRepo;

    @Test
    void quiz_flow() {
        User st = new User();
        st.setName("S");
        st.setEmail("s2@e.com");
        st.setRole(UserRole.STUDENT);
        userRepo.save(st);
        User te = new User();
        te.setName("T");
        te.setEmail("t2@e.com");
        te.setRole(UserRole.TEACHER);
        userRepo.save(te);

        Category cat = new Category();
        cat.setName("TestCat");
        catRepo.save(cat);
        Course c = new Course();
        c.setTitle("C");
        c.setDescription("D");
        c.setCategory(cat);
        c.setTeacher(te);
        courseRepo.save(c);
        CourseModule m = new CourseModule();
        m.setCourse(c);
        m.setTitle("M");
        m.setOrderIndex(1);
        moduleRepo.save(m);

        Quiz q = quizService.createQuiz(m.getId(), "Q1");
        Question ques = quizService.addQuestion(q.getId(), "What is JVM?", "SINGLE_CHOICE");
        AnswerOption o1 = quizService.addOption(ques.getId(), "Java Virtual Machine", true);
        quizService.addOption(ques.getId(), "Java Vendor Manager", false);

        QuizSubmission sub = quizService.submitQuiz(q.getId(), st.getId(), 100);
        assertEquals(100, sub.getScore());
        assertTrue(qsRepo.findByQuizAndStudent(q, st).isPresent());
    }
}
