package ru.otus.tester.exceptions;

public class CreateQuestionException extends NumberFormatException {
    @SuppressWarnings("unused")
    public CreateQuestionException() {
        super();
    }

    public CreateQuestionException(String s) {
        super(s);
    }
}
