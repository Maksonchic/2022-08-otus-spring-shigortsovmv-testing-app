package ru.otus.tester.controller;

import ru.otus.tester.domain.Question;

public interface QuestionsHandler {
    boolean hasNext();
    Question getNext();
    int getTasksCount();
    void init();
}
