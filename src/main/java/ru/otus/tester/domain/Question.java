package ru.otus.tester.domain;

import org.jetbrains.annotations.NotNull;
import ru.otus.tester.exceptions.CreateQuestionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question {

    private final int taskId;
    private final String taskBody;
    private final List<String> answers;
    private final String answerRight;

    public Question(String... args) throws CreateQuestionException {
        if (args.length < 4) {
            throw new CreateQuestionException("so few options");
        }
        this.taskId = Integer.parseInt(args[0]);
        this.taskBody = args[1];
        this.answers = Arrays
                .stream(args, 2, args.length - 1)
                .collect(Collectors.toList());
        this.answerRight = args[args.length - 1];
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskBody() {
        return taskBody;
    }

    public boolean checkAnswer(@NotNull String answer) {
        return answer.equals(answerRight);
    }

}
