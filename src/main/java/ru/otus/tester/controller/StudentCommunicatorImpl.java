package ru.otus.tester.controller;

import ru.otus.tester.model.Question;

import java.util.Scanner;

public class StudentCommunicatorImpl implements StudentCommunicator {

    @Override
    public String askLastName() {
        System.out.println("What is your surname?");
        return this.getAnswer();
    }

    @Override
    public String askFirstName() {
        System.out.println("What is your name?");
        return this.getAnswer();
    }

    @Override
    public String askQuestion(Question task) {
        System.out.printf("Q №%d: %s?\r\n1:\t%s\r\n2:\t%s\r\n3:\t%s\r\n4:\t%s%n",
                task.getTaskId(),
                task.getTaskBody(),
                task.getAnswer1(),
                task.getAnswer2(),
                task.getAnswer3(),
                task.getAnswer4());

        System.out.print("answer: ");
        return this.getAnswer();
    }

    public String getAnswer() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
