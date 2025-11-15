package com.example.edu.it;

import com.example.edu.domain.course.Category;
import com.example.edu.domain.course.Course;
import com.example.edu.domain.user.User;
import com.example.edu.domain.user.UserRole;
import com.example.edu.repo.CategoryRepository;
import com.example.edu.repo.CourseRepository;
import com.example.edu.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class CourseCrudIT extends PostgresContainerBase {

    @Autowired UserRepository userRepo;
    @Autowired CategoryRepository catRepo;
    @Autowired CourseRepository courseRepo;

    @PersistenceContext EntityManager em;

    @Test
    void createReadUpdateDelete_course() {
        User teacher = new User();
        teacher.setName("Alice");
        teacher.setEmail("alice@example.com");
        teacher.setRole(UserRole.TEACHER);
        userRepo.save(teacher);

        Category cat = new Category();
        cat.setName("Programming");
        catRepo.save(cat);

        Course c = new Course();
        c.setTitle("Java Basics");
        c.setDescription("Intro");
        c.setCategory(cat);
        c.setTeacher(teacher);
        c = courseRepo.save(c);

        Long id = c.getId();
        assertNotNull(id);

        Course found = courseRepo.findById(id).orElseThrow();
        assertEquals("Java Basics", found.getTitle());

        found.setTitle("Java Basics Updated");
        courseRepo.save(found);

        courseRepo.deleteById(id);
        assertTrue(courseRepo.findById(id).isEmpty());
    }

    @Test
    void lazyLoading_shouldFailOutsideTx() {
        User teacher = new User();
        teacher.setName("Bob");
        teacher.setEmail("bob@example.com");
        teacher.setRole(UserRole.TEACHER);
        userRepo.save(teacher);

        Category cat = new Category();
        cat.setName("Data");
        catRepo.save(cat);

        Course c = new Course();
        c.setTitle("Databases 101");
        c.setDescription("DB course");
        c.setCategory(cat);
        c.setTeacher(teacher);
        c = courseRepo.save(c);
        Course detached = courseRepo.findById(c.getId()).orElseThrow();
        em.detach(detached);

        assertThrows(org.hibernate.LazyInitializationException.class, () -> detached.getCategory().getName());
    }

    @Test
    @Transactional
    void lazyLoading_shouldWorkInsideTx() {
        User t = new User();
        t.setName("Carl");
        t.setEmail("carl@example.com");
        t.setRole(UserRole.TEACHER);
        userRepo.save(t);

        Category cat = new Category();
        cat.setName("QA");
        catRepo.save(cat);

        Course c = new Course();
        c.setTitle("Testing");
        c.setDescription("QA intro");
        c.setCategory(cat);
        c.setTeacher(t);
        c = courseRepo.save(c);

        // Внутри @Transactional доступ к LAZY-связи работает
        Course managed = courseRepo.findById(c.getId()).orElseThrow();
        assertEquals("QA", managed.getCategory().getName());
    }
}
