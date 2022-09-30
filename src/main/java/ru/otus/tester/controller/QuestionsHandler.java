package ru.otus.tester.controller;

import ru.otus.tester.domain.Question;

import java.io.IOException;

public interface QuestionsHandler {
    boolean hasNext();
    Question getNext();
    int getTasksCount();
    void init();
}
