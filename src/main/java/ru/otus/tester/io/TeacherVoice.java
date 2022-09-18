package ru.otus.tester.io;

import ru.otus.tester.model.Student;

public interface TeacherVoice {
    void sayVerdict(Student student, int grade, boolean verdict);
}
