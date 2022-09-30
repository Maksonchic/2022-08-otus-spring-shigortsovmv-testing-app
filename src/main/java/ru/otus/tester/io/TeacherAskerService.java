package ru.otus.tester.io;

import ru.otus.tester.domain.Question;
import ru.otus.tester.domain.Student;

import java.io.PrintStream;

public class TeacherAskerService implements TeacherAsker {

    private final PrintStream out;
    private final StudentCommunicator studentCommunicator;

    public TeacherAskerService(PrintStream out, StudentCommunicator studentCommunicator) {
        this.out = out;
        this.studentCommunicator = studentCommunicator;
    }

    @Override
    public String askLastName() {
        this.out.println("What is your surname?");
        return this.studentCommunicator.getAnswer();
    }

    @Override
    public String askFirstName() {
        this.out.println("What is your name?");
        return this.studentCommunicator.getAnswer();
    }

    @Override
    public String askQuestion(Question task) {
        this.out.printf("Q №%d: %s?\r\n1:\t%s\r\n2:\t%s\r\n3:\t%s\r\n4:\t%s%n",
                task.getTaskId(),
                task.getTaskBody(),
                task.getAnswer1(),
                task.getAnswer2(),
                task.getAnswer3(),
                task.getAnswer4());

        this.out.print("answer: ");
        return this.studentCommunicator.getAnswer();
    }

    @Override
    public void sayTestVerdict(Student student, int grade, boolean success) {
        this.out.printf("%s %s, your grade: %s of 5, %s"
                , student.getLastName()
                , student.getFirstName()
                , grade
                , success ? "congratulations!" : "try again((");
    }

    @Override
    public void say(String text) {
        this.out.println(text);
    }
}
