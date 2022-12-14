package ru.otus.tester.domain;

import ru.otus.tester.exceptions.CreateQuestionException;
import ru.otus.tester.exceptions.WorkQuestionsException;

import java.util.Arrays;
import java.util.List;

public class Question {

    private final int taskId;
    private final String taskBody;
    private final List<String> answers;
    private final int answerRight;

    public Question(
            int taskId,
            String taskBody,
            String answerRightNum,
            String... answers) throws CreateQuestionException {
        this.taskId = taskId;
        this.taskBody = taskBody;
        this.answerRight = Integer.parseInt(answerRightNum);
        this.answers = Arrays.asList(answers);
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

    public boolean checkAnswer(String answer) throws WorkQuestionsException {
        try {
            return answerRight == Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            throw new WorkQuestionsException(e);
        }
    }

}
