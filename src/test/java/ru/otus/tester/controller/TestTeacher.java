package ru.otus.tester.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.tester.exceptions.WorkQuestionsException;
import ru.otus.tester.io.TeacherAskerService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestTeacher {

    @MockBean
    private TeacherAskerService teacherAsker;

    @MockBean
    private QuestionsHandlerService questionsHandler;

    @Autowired
    private Teacher teacher;

    @Test
    @DisplayName("Не должно быть исключений, все ответы студента - 2")
    void notThrowsWorkExc() {
        Mockito
                .when(teacherAsker.askQuestion(any()))
                .thenReturn("2");

        assertDoesNotThrow(teacher::letsTesting);

        // TODO think about
        Mockito.verify(teacherAsker, Mockito.times(1)).askFirstName();
        Mockito.verify(teacherAsker, Mockito.times(5)).askQuestion(any());
        Mockito.verify(questionsHandler, Mockito.times(0)).hasNext();
    }

    @Test
    @DisplayName("Сваливаемся на ответе null")
    void throwsWorkExc() {
        Mockito
                .when(teacherAsker.askQuestion(any()))
                .thenReturn(null);

        assertThrows(WorkQuestionsException.class, teacher::letsTesting);
    }
}

