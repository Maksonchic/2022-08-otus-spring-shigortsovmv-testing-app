package ru.otus.tester.controller;

import ru.otus.tester.model.Question;

public interface Communicator {
    void ask(Question task);
    String getAnswer();
}
