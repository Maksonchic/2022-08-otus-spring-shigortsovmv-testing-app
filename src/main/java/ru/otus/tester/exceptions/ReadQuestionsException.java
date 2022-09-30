package ru.otus.tester.exceptions;

public class ReadQuestionsException extends RuntimeException {
    public ReadQuestionsException() {
        super();
    }

    public ReadQuestionsException(String message) {
        super(message);
    }

    public ReadQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadQuestionsException(Throwable cause) {
        super(cause);
    }

    public ReadQuestionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
