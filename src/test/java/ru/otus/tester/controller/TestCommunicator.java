package ru.otus.tester.controller;

import ru.otus.tester.io.StudentCommunicator;
import ru.otus.tester.model.Question;

public class TestCommunicator implements StudentCommunicator {

    private String rightAnswer;

    @Override
    public String askLastName() {
        return "LastName_here";
    }

    @Override
    public String askFirstName() {
        return "FirstName_here";
    }

    @Override
    public String askQuestion(Question task) {
        this.rightAnswer = task.getRight();
        return task.getRight();
    }

    @Override
    public String getAnswer() {
        return this.rightAnswer;
    }
}
