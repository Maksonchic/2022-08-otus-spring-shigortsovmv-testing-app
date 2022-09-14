package ru.otus.tester.controller;

import ru.otus.tester.model.Question;

public class Teacher {

    private final Questions tasks;
    private final Communicator comm;

    public Teacher(Questions tasks, Communicator comm) {
        this.tasks = tasks;
        this.comm = comm;
    }

    public int letsTesting() {
        Question curTask;
        int rights = 0;

        while (tasks.hasNext()) {
            // get question
            curTask = tasks.getNext();
            // ask question
            comm.ask(curTask);
            if (curTask.getRight().equals(comm.getAnswer())) {
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
