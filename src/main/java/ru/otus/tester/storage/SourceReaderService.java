package ru.otus.tester.storage;

import ru.otus.tester.controller.QuestionsHandlerService;
import ru.otus.tester.exceptions.ReadQuestionsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SourceReaderService implements SourceReader {

    private final String questionsFile;

    public SourceReaderService(String questionsFile) {
        this.questionsFile = questionsFile;
    }

    @Override
    public List<String> getQuestionsDataList() {
        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(
                                this.getQuestionsStream()));


        List<String> tasks = new ArrayList<>();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            throw new ReadQuestionsException(e);
        }

        return tasks;
    }

    private InputStream getQuestionsStream() {
        return QuestionsHandlerService.class.getResourceAsStream(this.questionsFile);
    }

}
