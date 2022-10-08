package ru.otus.tester.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.tester.io.TeacherAskerService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class TestTeacher {

    @MockBean
    private TeacherAskerService teacherAsker;

    @MockBean
    private ResultCalculatorService resultCalculator;

    @Autowired
    private Teacher teacher;

    @Test
    void notThrowsWorkExc() {
        Mockito
                .when(teacherAsker.askQuestion(any()))
                .thenReturn("2");

        assertDoesNotThrow(teacher::letsTesting);
    }
}

