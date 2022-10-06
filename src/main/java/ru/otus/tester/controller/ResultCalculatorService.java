package ru.otus.tester.controller;

import ru.otus.tester.config.QuestProps;

public class ResultCalculatorService implements ResultCalculator {

    private int rightAnswers;
    private final int tasksCount;
    private final int successPercent;

    public ResultCalculatorService(QuestProps appProps, QuestionsHandler questionsHandler) {
        this.successPercent = appProps.getSuccessCountPercent();
        this.tasksCount = questionsHandler.getTasksCount();
    }

    @Override
    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    @Override
    public int calcGrade() {
        if (this.tasksCount == 0) return 5;
        int grade = this.rightAnswers * 5 / this.tasksCount;
        return Math.max(grade, 1);
    }

    @Override
    public boolean calcSuccess() {
        if (this.tasksCount == 0) return true;
        return this.rightAnswers * 100 / this.tasksCount > this.successPercent;
    }
}
