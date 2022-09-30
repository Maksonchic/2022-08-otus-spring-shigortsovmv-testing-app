package ru.otus.tester.storage;

import ru.otus.tester.domain.Question;

import java.util.List;

public interface ResourceProvider {
    List<Question> createQuestionsList();
}
