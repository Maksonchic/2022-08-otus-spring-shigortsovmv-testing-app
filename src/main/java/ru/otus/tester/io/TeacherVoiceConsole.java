package ru.otus.tester.io;

import ru.otus.tester.model.Student;

public class TeacherVoiceConsole implements TeacherVoice {

    @Override
    public void sayTestVerdict(Student student, int grade, boolean success) {
        System.out.printf("%s %s, your grade: %s of 5, %s"
                , student.getLastName()
                , student.getFirstName()
                , grade
                , success ? "congratulations!" : "try again((");
    }

    @Override
    public void say(String text) {
        System.out.println(text);
    }
}
