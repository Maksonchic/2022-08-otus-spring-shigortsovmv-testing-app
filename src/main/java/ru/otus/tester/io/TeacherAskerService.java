package ru.otus.tester.io;

import org.springframework.beans.factory.annotation.Value;
import ru.otus.tester.domain.Question;
import ru.otus.tester.domain.Student;

import java.io.PrintStream;

public class TeacherAskerService implements TeacherAsker {

    private final PrintStream out;
    private final StudentCommunicator studentCommunicator;

    public TeacherAskerService(
            @Value("#{T(java.lang.System).out}") PrintStream out,
            StudentCommunicator studentCommunicator) {
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
        this.out.printf("Question number %d: %s?", task.getTaskId(), task.getTaskBody());

        for (int i = 0; i < task.getAnswers().size(); i++) {
            this.out.printf("%s%d:\t%s", System.lineSeparator(), i + 1, task.getAnswers().get(i));
        }
        this.out.print(System.lineSeparator());

        this.out.print("answer number: ");
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
