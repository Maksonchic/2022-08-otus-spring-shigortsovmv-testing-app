package ru.otus.tester.controller;

import ru.otus.tester.io.StudentVoiceCommunicator;
import ru.otus.tester.io.TeacherVoice;
import ru.otus.tester.model.Question;
import ru.otus.tester.model.Student;

public class Teacher {

    private final int successPercent;

    private final Questions tasks;
    private final StudentVoiceCommunicator comm;
    private final TeacherVoice voice;

    public Teacher(Questions tasks, StudentVoiceCommunicator comm, TeacherVoice voice, int successPercent) {
        this.tasks = tasks;
        this.comm = comm;
        this.voice = voice;
        this.successPercent = successPercent;
    }

    public int letsTesting() {
        Student curStudent = new Student(this.comm.askFirstName(), this.comm.askLastName());

        // get student right answers
        int rights = this.getTestAnswersAndValidateThem();

        // calculate student grade 0 - 5
        int grade = this.calcGrade(rights);

        // calc verdict
        boolean verdict = this.calcSuccess(rights);

        // say it
        voice.sayTestVerdict(curStudent, grade, verdict);

        return grade;
    }

    private int getTestAnswersAndValidateThem() {
        Question curTask;
        int rights = 0;

        while (tasks.hasNext()) {
            // get question
            curTask = tasks.getNext();
            // ask question
            String answer = comm.askQuestion(curTask);
            if (curTask.getRight().equals(answer)) {
                rights += 1;
                this.voice.say("YES");
            } else {
                this.voice.say("NO");
            }
        }

        return rights;
    }

    private int calcGrade(int rights) {
        if (tasks.getTasksCount() == 0) return 5;
        int grade = rights * 5 / tasks.getTasksCount();
        //noinspection ManualMinMaxCalculation
        return grade < 1 ? 1 : grade;
    }

    private boolean calcSuccess(int rights) {
        if (tasks.getTasksCount() == 0) return true;
        return rights * 100 / this.tasks.getTasksCount() > this.successPercent;
    }
}
