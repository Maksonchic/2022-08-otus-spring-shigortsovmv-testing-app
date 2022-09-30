package ru.otus.tester.controller.io;

import ru.otus.tester.domain.Question;
import ru.otus.tester.io.TeacherAsker;
import ru.otus.tester.domain.Student;

public class TestTeacherAskerService implements TeacherAsker {

    @Override
    public String askLastName() {
        return null;
    }

    @Override
    public String askFirstName() {
        return null;
    }

    @Override
    public String askQuestion(Question task) {
        return "2";
    }

    @Override
    public void sayTestVerdict(Student student, int grade, boolean verdict) {

    }

    @Override
    public void say(String text) {

    }
}
