package ru.otus.tester.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.tester.config.Localizator;
import ru.otus.tester.domain.Question;
import ru.otus.tester.domain.Student;

import java.io.PrintStream;

@Service
public class TeacherAskerService implements TeacherAsker {

    private final StudentCommunicator studentCommunicator;
    private final PrintStream out;
    private final Localizator converter;

    public TeacherAskerService(
            StudentCommunicator studentCommunicator,
            @Value("#{T(java.lang.System).out}") PrintStream out,
            Localizator converter) {
        this.studentCommunicator = studentCommunicator;
        this.out = out;
        this.converter = converter;
    }

    @Override
    public String askLastName() {
        this.out.println(this.converter.localize("teacher.whats-surname"));
        return this.studentCommunicator.getAnswer();
    }

    @Override
    public String askFirstName() {
        this.out.println(this.converter.localize("teacher.whats-name"));
        return this.studentCommunicator.getAnswer();
    }

    @Override
    public String askQuestion(Question task) {
        this.out.print(this.converter.localize(
                "teacher.question-number", task.getTaskId(), task.getTaskBody()));

        for (int i = 0; i < task.getAnswers().size(); i++) {
            this.out.printf("%s%d:\t%s", System.lineSeparator(), i + 1, task.getAnswers().get(i));
        }
        this.out.println();

        this.out.print(this.converter.localize("teacher.answer-number"));
        return this.studentCommunicator.getAnswer();
    }

    @Override
    public void sayTestVerdict(Student student, int grade, boolean success) {
        this.out.print(
                this.converter.localize("teacher.grade-final"
                        , student.getLastName()
                        , student.getFirstName()
                        , grade) + ". ");

        this.out.println(this.converter.localize(
                success ? "teacher.success-final-yes" : "teacher.success-final-no"));
    }

    @Override
    public void say(String textCode) {
        this.out.println(this.converter.localize(textCode));
    }
}
