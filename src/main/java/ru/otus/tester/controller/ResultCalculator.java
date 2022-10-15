package ru.otus.tester.controller;

public interface ResultCalculator {
    int calcGrade();
    boolean calcSuccess();
    void setCurParams(int rightAnswers, int tasksCount);
}
