package ru.otus.tester.controller;

import org.springframework.stereotype.Service;
import ru.otus.tester.domain.Question;
import ru.otus.tester.storage.ResourceProvider;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsHandlerService implements QuestionsHandler {

    private int curTaskNumber = 0;

    private final List<Question> tasks;

    private final ResourceProvider resourceProvider;

    public QuestionsHandlerService(ResourceProvider sourceReader) {
        this.resourceProvider = sourceReader;
        this.tasks = new ArrayList<>();
    }

    @Override
    @PostConstruct
    public void init() {
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
