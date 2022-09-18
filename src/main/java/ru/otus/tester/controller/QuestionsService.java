package ru.otus.tester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.otus.tester.model.Question;
import ru.otus.tester.storage.SourceReader;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionsService implements Questions {

    private int curTaskNumber = 0;

    private final ArrayList<Question> tasks;

    @Value("${questions.file}")
    private String questFile;
    @Autowired
    private SourceReader sourceReader;

    public QuestionsService() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void init() throws IOException, NullPointerException {
        this.tasks.addAll(sourceReader.createQuestionsListFromFile(this.questFile));
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
