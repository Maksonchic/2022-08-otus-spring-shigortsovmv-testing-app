package ru.otus.tester.storage;

import ru.otus.tester.domain.Question;
import ru.otus.tester.exceptions.ReadQuestionsException;

import java.util.Arrays;
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
                .map(line -> {
                    String[] words = line.split("\\|");
                    assert words.length > 4;
                    return new Question(words[0], words[1], words[2], Arrays.copyOfRange(words, 3, words.length));
                })
                .collect(Collectors.toList());
    }
}
