package ru.otus.tester.controller;

import ru.otus.tester.model.IQuestion;

import java.io.IOException;

public interface IQuestions {
    boolean hasNext();
    IQuestion getNext();
    int getTaskCount();
    void init() throws IOException;
}
