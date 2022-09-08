package ru.otus.tester.controller;

import ru.otus.tester.model.Question;

import java.io.IOException;

public interface Questions {
    boolean hasNext();
    Question getNext();
    int getTaskCount();
    void init() throws IOException;
}
