package ru.otus.tester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.tester.model.Question;

public class Teacher {

    @Autowired
    private final Questions tasks;
    @Autowired
    private final StudentCommunicator comm;
    private final int successPercent;

    public Teacher(Questions tasks, StudentCommunicator comm, int successPercent) {
        this.tasks = tasks;
        this.comm = comm;
        this.successPercent = successPercent;
    }

    public int letsTesting() {
        this.comm.askLastName();
        this.comm.askFirstName();

        Question curTask;
        int rights = 0;

        while (tasks.hasNext()) {
            // get question
            curTask = tasks.getNext();
            // ask question
            String answer = comm.askQuestion(curTask);
            if (curTask.getRight().equals(answer)) {
                rights += 1;
                System.out.println("YES\r\n");
            } else {
                System.out.println("NO\r\n");
            }
        }

        // calculate student grade
        int grade = rights * 5 / tasks.getTasksCount();

        // get verdict
        boolean verdict = this.calculateSuccess(rights);

        // show it
        sayVerdict(grade, verdict);

        return grade;
    }

    private void sayVerdict(int grade, boolean success) {
        System.out.printf("Your grade: %s of 5, %s", grade, success ? "congratulations!" : "try again((");
    }

    private boolean calculateSuccess(int rights) {
        return rights * 100 / this.tasks.getTasksCount() > this.successPercent;
    }
}
