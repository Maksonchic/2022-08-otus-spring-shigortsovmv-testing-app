package ru.otus.tester.storage;

import org.springframework.stereotype.Component;
import ru.otus.tester.domain.Question;
import ru.otus.tester.exceptions.CreateQuestionException;
import ru.otus.tester.exceptions.ReadQuestionsException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResourceProviderImpl implements ResourceProvider {

    private final SourceReader sourceReader;

    public ResourceProviderImpl(final SourceReader sourceReader) {
        this.sourceReader = sourceReader;
    }

    @Override
    public List<Question> createQuestionsList() throws ReadQuestionsException {
        // convert List<String> to List<Question>
        return this.sourceReader
                .getQuestionsDataList()
                .stream()
                .map(line -> createQuestionSingle(line.split("\\|")))
                .collect(Collectors.toList());
    }

    private Question createQuestionSingle(final String[] parts) throws CreateQuestionException {
        if (parts.length < 4) {
            throw new CreateQuestionException("so few question options");
        }
        int taskId = Integer.parseInt(parts[0]);
        String taskBody = parts[1];
        String answerNumRight = parts[2];
        String[] answers = Arrays.copyOfRange(parts, 3, parts.length);
        return new Question(taskId, taskBody, answerNumRight, answers);
    }
}
