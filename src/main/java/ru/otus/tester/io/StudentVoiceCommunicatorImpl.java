package ru.otus.tester.io;

import ru.otus.tester.model.Question;

import java.io.PrintStream;
import java.util.Scanner;

public class StudentVoiceCommunicatorImpl implements StudentVoiceCommunicator {

    private final PrintStream studentWatchSource;

    public StudentVoiceCommunicatorImpl() {
        this.studentWatchSource = System.out;
    }

    @Override
    public String askLastName() {
        this.studentWatchSource.println("What is your surname?");
        return this.getAnswer();
    }

    @Override
    public String askFirstName() {
        this.studentWatchSource.println("What is your name?");
        return this.getAnswer();
    }

    @Override
    public String askQuestion(Question task) {
        this.studentWatchSource.printf("Q №%d: %s?\r\n1:\t%s\r\n2:\t%s\r\n3:\t%s\r\n4:\t%s%n",
                task.getTaskId(),
                task.getTaskBody(),
                task.getAnswer1(),
                task.getAnswer2(),
                task.getAnswer3(),
                task.getAnswer4());

        this.studentWatchSource.print("answer: ");
        return this.getAnswer();
    }

    public String getAnswer() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
