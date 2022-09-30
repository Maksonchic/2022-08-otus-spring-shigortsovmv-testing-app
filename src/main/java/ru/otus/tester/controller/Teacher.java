package ru.otus.tester.controller;

import ru.otus.tester.io.TeacherAsker;
import ru.otus.tester.domain.Question;
import ru.otus.tester.domain.Student;

public class Teacher {

    private final int successPercent;
    private final QuestionsHandler tasks;
    private final TeacherAsker teacherAsker;

    public Teacher(QuestionsHandler tasks, TeacherAsker teacherAsker, int successPercent) {
        this.tasks = tasks;
        this.teacherAsker = teacherAsker;
        this.successPercent = successPercent;
    }

    public void letsTesting() {
        String studentFirstName = this.teacherAsker.askFirstName();
        String studentLastName = this.teacherAsker.askLastName();

        Student curStudent = new Student(studentFirstName, studentLastName);

        int rights = this.getRightCount();
        int grade = this.calcGrade(rights); // student grade 0 - 5
        boolean verdict = this.calcSuccess(rights);

        this.teacherAsker.sayTestVerdict(curStudent, grade, verdict);
    }

    private int getRightCount() {
        Question curTask;
        int rights = 0;

        while (tasks.hasNext()) {
            // get question
            curTask = tasks.getNext();
            // ask question
            String answer = this.teacherAsker.askQuestion(curTask);
            if (curTask.checkAnswer(answer)) {
                rights += 1;
                this.teacherAsker.say("YES");
            } else {
                this.teacherAsker.say("NO");
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
