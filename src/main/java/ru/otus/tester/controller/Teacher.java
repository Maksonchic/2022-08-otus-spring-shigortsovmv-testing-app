package ru.otus.tester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.tester.model.Question;

public class Teacher {

    @Autowired
    private final Questions tasks;
    @Autowired
    private final StudentCommunicator comm;

    public Teacher(Questions tasks, StudentCommunicator comm) {
        this.tasks = tasks;
        this.comm = comm;
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
        int grade = rights * 5 / tasks.getTaskCount();

        // show it
        sayVerdict(grade);

        return grade;
    }

    private void sayVerdict(int grade) {
        System.out.println("Your grade: " + grade);
    }
}
