package ru.otus.tester.controller;

import ru.otus.tester.io.StudentCommunicator;
import ru.otus.tester.io.TeacherVoice;
import ru.otus.tester.model.Question;
import ru.otus.tester.model.Student;

public class Teacher {

    private final int successPercent;

    private String curStudentFirstName;
    private String curStudentLastName;

    private Student curStudent;

    private final Questions tasks;
    private final StudentCommunicator comm;
    private final TeacherVoice voice;

    public Teacher(Questions tasks, StudentCommunicator comm, TeacherVoice voice, int successPercent) {
        this.tasks = tasks;
        this.comm = comm;
        this.voice = voice;
        this.successPercent = successPercent;
    }

    public int letsTesting() {
        this.curStudent = new Student(this.comm.askFirstName(), this.comm.askLastName());

        // get right answers
        int rights = this.getTestAnswers();

        // calculate student grade
        int grade = this.calcGrade(rights);

        // calc verdict
        boolean verdict = this.calcSuccess(rights);

        // say it
        voice.sayVerdict(this.curStudent, grade, verdict);

        return grade;
    }

    private int getTestAnswers() {
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

        return rights;
    }

    private int calcGrade(int rights) {
        if (tasks.getTasksCount() == 0) return 5;
        return rights * 5 / tasks.getTasksCount();
    }

    private boolean calcSuccess(int rights) {
        if (tasks.getTasksCount() == 0) return true;
        return rights * 100 / this.tasks.getTasksCount() > this.successPercent;
    }
}
