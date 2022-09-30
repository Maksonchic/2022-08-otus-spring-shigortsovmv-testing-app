package ru.otus.tester.io;

import ru.otus.tester.domain.Question;
import ru.otus.tester.domain.Student;

public interface TeacherAsker {
    String askLastName();
    String askFirstName();
    String askQuestion(Question task);
    void sayTestVerdict(Student student, int grade, boolean verdict);
    void say(String text);
}
