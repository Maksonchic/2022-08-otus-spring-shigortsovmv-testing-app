package ru.otus.tester.controller;

public interface ResultCalculator {
    int calcGrade();
    boolean calcSuccess();
    void setRightAnswers(int rightAnswers);
}
