package ru.otus.tester.io;

import ru.otus.tester.model.Student;

public class TeacherVoiceConsole implements TeacherVoice {
    @Override
    public void sayVerdict(Student student, int grade, boolean success) {
        System.out.printf("%s %s, your grade: %s of 5, %s"
                , student.getLastName()
                , student.getFirstName()
                , grade
                , success ? "congratulations!" : "try again((");
    }
}
