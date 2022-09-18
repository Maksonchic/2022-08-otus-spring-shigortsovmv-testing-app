package ru.otus.tester.controller;

import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.tester.controller.config.TestAppConfig;

public class TestTeacher {

    @DisplayName("Homework 2 test")
    @Test
    void test1_autoAnswer() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestAppConfig.class);
        Questions tasks = context.getBean(Questions.class);
        Teacher teacher = context.getBean(Teacher.class);

        int grade = teacher.letsTesting();
        Assertions.assertEquals(grade, tasks.getTasksCount());
    }
}

