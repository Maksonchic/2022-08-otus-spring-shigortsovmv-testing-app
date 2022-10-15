package ru.otus.tester.controller;

import org.springframework.stereotype.Service;
import ru.otus.tester.config.QuestProps;

@Service
public class ResultCalculatorService implements ResultCalculator {

    private int rightAnswers;

    private int tasksCount;

    private final int successPercent;

    public ResultCalculatorService(QuestProps appProps) {
        this.successPercent = appProps.getSuccessCountPercent();
    }

    @Override
    public void setCurParams(int rightAnswers, int tasksCount) {
        this.rightAnswers = rightAnswers;
        this.tasksCount = tasksCount;
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
