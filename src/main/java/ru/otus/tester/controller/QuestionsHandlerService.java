package ru.otus.tester.controller;

import ru.otus.tester.domain.Question;
import ru.otus.tester.storage.ResourceProvider;

import java.util.ArrayList;
import java.util.List;

public class QuestionsHandlerService implements QuestionsHandler {

    private int curTaskNumber = 0;

    private final List<Question> tasks;

    private final ResourceProvider sourceReader;

    public QuestionsHandlerService(ResourceProvider sourceReader) {
        this.sourceReader = sourceReader;
        this.tasks = new ArrayList<>();
    }

    @Override
    public void init() {
        this.tasks.addAll(sourceReader.createQuestionsList());
    }

    @Override
    public boolean hasNext() {
        return curTaskNumber < tasks.size();
    }

    @Override
    public Question getNext() {
        return this.hasNext() ? this.tasks.get(curTaskNumber++) : null;
    }

    @Override
    public int getTasksCount() {
        return this.tasks.size();
    }
}
