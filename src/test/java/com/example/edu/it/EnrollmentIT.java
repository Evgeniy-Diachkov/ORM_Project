package com.example.edu.it;

import com.example.edu.domain.course.Category;
import com.example.edu.domain.course.Course;
import com.example.edu.domain.user.User;
import com.example.edu.domain.user.UserRole;
import com.example.edu.repo.CategoryRepository;
import com.example.edu.repo.CourseRepository;
import com.example.edu.repo.UserRepository;
import com.example.edu.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnrollmentIT extends PostgresContainerBase {

    @Autowired EnrollmentService enrollmentService;
    @Autowired UserRepository userRepo;
    @Autowired CourseRepository courseRepo;
    @Autowired CategoryRepository catRepo;

    @Test
    void enroll_uniqueConstraint_okAndDuplicateFails() {
        User s = new User(); s.setName("Stud"); s.setEmail("stud@e.com"); s.setRole(UserRole.STUDENT);
        userRepo.save(s);
        User t = new User(); t.setName("Teach"); t.setEmail("teach@e.com"); t.setRole(UserRole.TEACHER);
        userRepo.save(t);

        Category cat = new Category(); cat.setName("Cat"); catRepo.save(cat);
        Course c = new Course(); c.setTitle("C1"); c.setDescription("D"); c.setCategory(cat); c.setTeacher(t);
        courseRepo.save(c);

        assertNotNull(enrollmentService.enroll(s.getId(), c.getId()));
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> enrollmentService.enroll(s.getId(), c.getId()));
        assertTrue(ex.getMessage().toLowerCase().contains("already"));
    }
}
