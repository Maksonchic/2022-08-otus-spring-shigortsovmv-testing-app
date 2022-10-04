package ru.otus.tester.controller;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.tester.controller.config.TestAppConfig;
import ru.otus.tester.exceptions.WorkQuestionsException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTeacher {
    @Test
    void test1_autoAnswer() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestAppConfig.class);
        Teacher teacher = context.getBean(Teacher.class);

        assertThrows(WorkQuestionsException.class, teacher::letsTesting);
    }
}

