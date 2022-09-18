package ru.otus.tester.io;

import ru.otus.tester.model.Question;

public interface StudentCommunicator {
    String askLastName();
    String askFirstName();
    String askQuestion(Question task);
    String getAnswer();
}
