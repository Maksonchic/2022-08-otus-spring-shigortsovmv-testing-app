package ru.otus.tester.storage;

import ru.otus.tester.domain.Question;
import ru.otus.tester.exceptions.ReadQuestionsException;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceProviderImpl implements ResourceProvider {

    private final SourceReader sourceReader;

    public ResourceProviderImpl(final SourceReader sourceReader) {
        this.sourceReader = sourceReader;
    }

    public List<Question> createQuestionsList() throws ReadQuestionsException {
        // convert List<String> to List<Question>
        return this.sourceReader
                .getQuestionsDataList()
                .stream()
                .map(line -> new Question(line.split("\\|")))
                .collect(Collectors.toList());
    }
}
