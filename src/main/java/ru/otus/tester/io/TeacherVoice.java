package ru.otus.tester.io;

import ru.otus.tester.model.Student;

public interface TeacherVoice {
    void sayTestVerdict(Student student, int grade, boolean verdict);
    void say(String text);
}
