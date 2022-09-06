package ru.otus.tester.controller;

import ru.otus.tester.model.IQuestion;
import ru.otus.tester.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Questions implements IQuestions {

    private int curTaskNumber = 0;
    private final ArrayList<IQuestion> tasks;

    private String questFile;

    public Questions() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void init() throws IOException, NullPointerException {
        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(
                                Objects.requireNonNull(
                                        Questions.class.getResourceAsStream(this.questFile))));
        String line = "";
        while ((line = br.readLine()) != null)
            this.tasks.add(new Question(line.split("\\|")));
    }

    public void setQuestFile(String questFile) {
        this.questFile = questFile;
    }

    @Override
    public boolean hasNext() {
        return curTaskNumber < tasks.size();
    }

    @Override
    public IQuestion getNext() {
        return this.hasNext() ? this.tasks.get(curTaskNumber++) : null;
    }

    @Override
    public int getTaskCount() {
        return this.tasks.size();
    }
}
