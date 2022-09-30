package ru.otus.tester.controller;

import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.tester.controller.config.TestAppConfig;

public class TestTeacher {

    @DisplayName("test is work")
    @Test
    void test1_autoAnswer() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestAppConfig.class);
        Teacher teacher = context.getBean(Teacher.class);

        teacher.letsTesting();
    }
}

