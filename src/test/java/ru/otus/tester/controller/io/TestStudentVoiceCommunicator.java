package ru.otus.tester.controller.io;

import ru.otus.tester.io.StudentVoiceCommunicator;
import ru.otus.tester.model.Question;

public class TestStudentVoiceCommunicator implements StudentVoiceCommunicator {

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
