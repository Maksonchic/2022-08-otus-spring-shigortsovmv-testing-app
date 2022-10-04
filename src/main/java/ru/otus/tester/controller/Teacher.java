package ru.otus.tester.controller;

import ru.otus.tester.domain.Question;
import ru.otus.tester.domain.Student;
import ru.otus.tester.io.TeacherAsker;

public class Teacher {

    private final QuestionsHandler questionsHandler;
    private final TeacherAsker teacherAsker;
    private final ResultCalculator resultCalculator;

    public Teacher(QuestionsHandler questionsHandler, TeacherAsker teacherAsker, ResultCalculator resultCalculator) {
        this.questionsHandler = questionsHandler;
        this.teacherAsker = teacherAsker;
        this.resultCalculator = resultCalculator;
    }

    public void letsTesting() {
        String studentFirstName = this.teacherAsker.askFirstName();
        String studentLastName = this.teacherAsker.askLastName();

        Student curStudent = new Student(studentFirstName, studentLastName);

        int rights = this.askQuestions();

        this.resultCalculator.setRightAnswers(rights);

        int grade = this.resultCalculator.calcGrade(); // student grade 0 - 5
        boolean verdict = this.resultCalculator.calcSuccess();

        this.teacherAsker.sayTestVerdict(curStudent, grade, verdict);
    }

    private int askQuestions() {
        Question curTask;
        int rights = 0;

        while (questionsHandler.hasNext()) {
            // get question
            curTask = questionsHandler.getNext();
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
}
