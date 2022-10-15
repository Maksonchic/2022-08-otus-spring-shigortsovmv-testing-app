package ru.otus.tester.exceptions;

public class WorkQuestionsException extends NumberFormatException {

    public WorkQuestionsException(NumberFormatException e) {
        super(e.getMessage());
    }

}
