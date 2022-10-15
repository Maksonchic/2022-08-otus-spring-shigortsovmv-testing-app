package ru.otus.tester.storage;

import org.springframework.stereotype.Service;
import ru.otus.tester.config.QuestProps;
import ru.otus.tester.controller.QuestionsHandlerService;
import ru.otus.tester.exceptions.ReadQuestionsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class SourceReaderService implements SourceReader {

    private final String questionsFile;

    public SourceReaderService(QuestProps appProps) {
        this.questionsFile = appProps.getFile();
    }

    @Override
    public List<String> getQuestionsDataList() {
        List<String> tasks;

        try(InputStream is = this.getQuestionsStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            tasks = new ArrayList<>();
            String line;
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
