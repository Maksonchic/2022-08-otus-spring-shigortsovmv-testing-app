package ru.otus.tester.controller;

import ru.otus.tester.model.Question;

public class TestCommunicator implements StudentCommunicator {

    private String rightAnswer;

    @Override
    public String askLastName() {
        return null;
    }

    @Override
    public String askFirstName() {
        return null;
    }

    @Override
    public String askQuestion(Question task) {
        this.rightAnswer = task.getRight();
        return "";
    }

    @Override
    public String getAnswer() {
        return this.rightAnswer;
    }
}
