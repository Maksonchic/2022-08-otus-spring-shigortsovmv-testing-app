package ru.otus.tester.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TestTeacher {

    @DisplayName("Single test successful")
    @Test
    void test1_autoAnswer() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Questions test = context.getBean(QuestionsService.class);
        Teacher teacher = context.getBean(Teacher.class);

        try {
            test.init();
            Assertions.assertEquals(teacher.letsTesting(), 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

