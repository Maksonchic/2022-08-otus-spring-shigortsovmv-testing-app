package ru.otus.tester.controller;

import org.springframework.stereotype.Service;
import ru.otus.tester.domain.Question;
import ru.otus.tester.storage.ResourceProvider;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsHandlerService implements QuestionsHandler {

    private int curTaskNumber;

    private List<Question> tasks;

    private final ResourceProvider resourceProvider;

    public QuestionsHandlerService(ResourceProvider sourceReader) {
        this.resourceProvider = sourceReader;
    }

    @Override
    public void init() {
        this.curTaskNumber = 0;
        this.tasks = new ArrayList<>();
        this.tasks.addAll(resourceProvider.createQuestionsList());
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
