package ru.otus.tester.controller;

import ru.otus.tester.model.Question;
import ru.otus.tester.storage.SourceReader;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionsService implements Questions {

    private int curTaskNumber = 0;

    private final ArrayList<Question> tasks;

    private String questFile;
    private SourceReader sourceReader;

    public QuestionsService() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void init() throws IOException, NullPointerException {
        this.tasks.addAll(sourceReader.createQuestionsList(this.questFile));
    }

    public void setQuestFile(String questFile) {
        this.questFile = questFile;
    }
    public void setSourceReader(SourceReader sourceReader) {
        this.sourceReader = sourceReader;
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
    public int getTaskCount() {
        return this.tasks.size();
    }
}
