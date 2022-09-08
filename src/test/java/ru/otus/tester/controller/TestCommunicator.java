package ru.otus.tester.controller;

import ru.otus.tester.model.Question;

public class TestCommunicator implements Communicator {

    private String rightAnswer;

    @Override
    public void ask(Question task) {
        this.rightAnswer = task.getRight();
    }

    @Override
    public String getAnswer() {
        return this.rightAnswer;
    }
}
