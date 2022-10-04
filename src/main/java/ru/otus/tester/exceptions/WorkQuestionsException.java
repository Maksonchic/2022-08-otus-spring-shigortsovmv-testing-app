package ru.otus.tester.exceptions;

public class WorkQuestionsException extends NumberFormatException {

    public WorkQuestionsException() {
        super();
    }

    public WorkQuestionsException(String s) {
        super(s);
    }

    public WorkQuestionsException(NumberFormatException e) {
        super(e.getMessage());
    }

}
