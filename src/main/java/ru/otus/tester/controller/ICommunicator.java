package ru.otus.tester.controller;

import ru.otus.tester.model.IQuestion;

public interface ICommunicator {
    void ask(IQuestion task);
    String getAnswer();
}
