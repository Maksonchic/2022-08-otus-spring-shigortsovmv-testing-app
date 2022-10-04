package ru.otus.tester.exceptions;

import java.io.IOException;

public class ReadQuestionsException extends NumberFormatException {
    public ReadQuestionsException() {
        super();
    }

    public ReadQuestionsException(String s) {
        super(s);
    }

    public ReadQuestionsException(IOException e) {
        super(e.getMessage());
    }
}
