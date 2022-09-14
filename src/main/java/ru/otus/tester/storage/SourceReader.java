package ru.otus.tester.storage;

import ru.otus.tester.controller.QuestionsService;
import ru.otus.tester.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class SourceReader {
    public ArrayList<Question> createQuestionsList(String file) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                QuestionsService.class.getResourceAsStream(file))));

        ArrayList<Question> tasks = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null)
            tasks.add(new Question(line.split("\\|")));
        return tasks;
    }
}
