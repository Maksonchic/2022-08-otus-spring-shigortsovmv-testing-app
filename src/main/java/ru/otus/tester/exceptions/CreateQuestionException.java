package ru.otus.tester.exceptions;

public class CreateQuestionException extends NumberFormatException {
    public CreateQuestionException() {
        super();
    }

    public CreateQuestionException(String s) {
        super(s);
    }
}
