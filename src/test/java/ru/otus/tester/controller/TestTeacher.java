package ru.otus.tester.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;
import ru.otus.tester.domain.Question;
import ru.otus.tester.exceptions.WorkQuestionsException;
import ru.otus.tester.io.TeacherAskerService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestTeacher {

    @MockBean
    private TeacherAskerService teacherAsker;

    @MockBean
    private QuestionsHandlerService questionsHandler;

    @SuppressWarnings("unused")
    @Autowired
    private Teacher teacher;

    private Question getQuestionPlug() {
        return new Question(1, "", "1", "");
    }

    @Test
    @DisplayName("Сваливаемся на ответе null")
    void throwsWorkExc() {
        when(teacherAsker.askQuestion(any())).thenReturn(null);
        when(questionsHandler.hasNext()).thenReturn(true);
        when(questionsHandler.getNext()).thenReturn(getQuestionPlug());

        assertThrows(WorkQuestionsException.class, teacher::letsTesting);
    }

    @Test
    @DisplayName("Не должно быть исключений, все ответы студента - 2")
    void notThrowsWorkExc() {
        when(teacherAsker.askQuestion(any())).thenReturn("2");
        when(questionsHandler.hasNext()).thenReturn(true);

        ReflectionTestUtils.setField(questionsHandler, "tasks", new ArrayList<Question>(){{
            add(getQuestionPlug());
            add(getQuestionPlug());
            add(getQuestionPlug());
            add(getQuestionPlug());
            add(getQuestionPlug());
        }});
        when(questionsHandler.hasNext()).thenCallRealMethod();
        when(questionsHandler.getNext()).thenCallRealMethod();

        assertDoesNotThrow(teacher::letsTesting);

        verify(teacherAsker, times(1)).askFirstName();
        verify(teacherAsker, times(5)).askQuestion(any());
        verify(questionsHandler, times(5)).getNext();
        verify(questionsHandler, times(11)).hasNext();
    }
}

