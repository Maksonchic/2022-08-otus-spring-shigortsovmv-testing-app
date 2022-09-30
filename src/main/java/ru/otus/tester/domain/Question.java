package ru.otus.tester.domain;

public class Question {

    private final int taskId;
    private final String taskBody;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final String answer4;
    private final String answerRight;

    public Question(int taskId,
                    String taskBody,
                    String answer1,
                    String answer2,
                    String answer3,
                    String answer4,
                    String answerRight) {
        this.taskId = taskId;
        this.taskBody = taskBody;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answerRight = answerRight;
    }

    public Question(String... args) {
        int taskIdTemp;
        try {
            taskIdTemp = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            taskIdTemp = -1;
        }
        this.taskId = taskIdTemp;
        this.taskBody = args[1];
        this.answer1 = args[2];
        this.answer2 = args[3];
        this.answer3 = args[4];
        this.answer4 = args[5];
        this.answerRight = args[6];
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskBody() {
        return taskBody;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public boolean checkAnswer(final String answer) {
        return answer.equals(answerRight);
    }

}
