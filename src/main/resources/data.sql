-- USERS -----------------------------------------------------------
INSERT INTO users (id, name, email, role, created_at, updated_at)
VALUES
    (1, 'Alice Teacher', 'alice@edu.com', 'TEACHER', now(), now()),
    (2, 'Bob Student', 'bob@edu.com', 'STUDENT', now(), now()),
    (3, 'Charlie Student', 'charlie@edu.com', 'STUDENT', now(), now());

-- PROFILES --------------------------------------------------------
INSERT INTO profiles (id, user_id, bio, avatar_url, created_at, updated_at)
VALUES
    (1, 1, 'Teacher of backend development', NULL, now(), now()),
    (2, 2, 'Student learning Java', NULL, now(), now()),
    (3, 3, 'Second student', NULL, now(), now());

-- CATEGORY --------------------------------------------------------
INSERT INTO categories (id, name, created_at, updated_at)
VALUES (1, 'Programming', now(), now());

-- COURSE ----------------------------------------------------------
INSERT INTO courses (id, title, description, category_id, teacher_id, created_at, updated_at)
VALUES (1, 'Java Fundamentals', 'Intro course for beginners', 1, 1, now(), now());

-- MODULE ----------------------------------------------------------
INSERT INTO modules (id, course_id, title, order_index, created_at, updated_at)
VALUES (1, 1, 'Basics of Java', 1, now(), now());

-- LESSON ----------------------------------------------------------
INSERT INTO lessons (id, module_id, title, content, created_at, updated_at)
VALUES (1, 1, 'Variables and Data Types', 'Introduction to variables', now(), now());

-- ASSIGNMENT ------------------------------------------------------
INSERT INTO assignments (id, lesson_id, title, description, max_score, created_at, updated_at)
VALUES (1, 1, 'Homework #1', 'Write a Java program with variables', 100, now(), now());

-- ENROLLMENTS -----------------------------------------------------
INSERT INTO enrollments (id, student_id, course_id, status, enrol_date, created_at, updated_at)
VALUES
    (1, 2, 1, 'ACTIVE', now(), now(), now()),
    (2, 3, 1, 'ACTIVE', now(), now(), now());

-- QUIZ ------------------------------------------------------------
INSERT INTO quizzes (id, module_id, title, created_at, updated_at)
VALUES (1, 1, 'Java Basics Quiz', now(), now());

-- QUESTION --------------------------------------------------------
INSERT INTO questions (id, quiz_id, text, type, created_at, updated_at)
VALUES (1, 1, 'Which keyword defines a class in Java?', 'SINGLE_CHOICE', now(), now());

-- ANSWER OPTIONS --------------------------------------------------
INSERT INTO answer_options (id, question_id, text, is_correct, created_at, updated_at)
VALUES
    (1, 1, 'function', false, now(), now()),
    (2, 1, 'def', false, now(), now()),
    (3, 1, 'class', true, now(), now());

-- Сброс для USERS (максимальный ID = 3)
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users), true);

-- Сброс для PROFILES (максимальный ID = 3)
SELECT setval('profiles_id_seq', (SELECT MAX(id) FROM profiles), true);

-- Сброс для CATEGORIES (максимальный ID = 1)
SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories), true);

-- Сброс для COURSES (максимальный ID = 1)
SELECT setval('courses_id_seq', (SELECT MAX(id) FROM courses), true);

-- Сброс для MODULES (максимальный ID = 1)
SELECT setval('modules_id_seq', (SELECT MAX(id) FROM modules), true);

-- Сброс для LESSONS (максимальный ID = 1)
SELECT setval('lessons_id_seq', (SELECT MAX(id) FROM lessons), true);

-- Сброс для ASSIGNMENTS (максимальный ID = 1)
SELECT setval('assignments_id_seq', (SELECT MAX(id) FROM assignments), true);

-- Сброс для ENROLLMENTS (максимальный ID = 2)
SELECT setval('enrollments_id_seq', (SELECT MAX(id) FROM enrollments), true);

-- Сброс для QUIZZES (максимальный ID = 1)
SELECT setval('quizzes_id_seq', (SELECT MAX(id) FROM quizzes), true);

-- Сброс для QUESTIONS (максимальный ID = 1)
SELECT setval('questions_id_seq', (SELECT MAX(id) FROM questions), true);

-- Сброс для ANSWER_OPTIONS (максимальный ID = 3)
SELECT setval('answer_options_id_seq', (SELECT MAX(id) FROM answer_options), true);