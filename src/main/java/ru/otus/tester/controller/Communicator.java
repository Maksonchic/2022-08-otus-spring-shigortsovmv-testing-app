package ru.otus.tester.controller;

import ru.otus.tester.model.IQuestion;

import java.util.Scanner;

public class Communicator implements ICommunicator {

    @Override
    public void ask(IQuestion task) {
        System.out.printf("Q №%d: %s?\r\n1:\t%s\r\n2:\t%s\r\n3:\t%s\r\n4:\t%s%n",
                task.getTaskId(),
                task.getTaskBody(),
                task.getAnswer1(),
                task.getAnswer2(),
                task.getAnswer3(),
                task.getAnswer4());

        System.out.print("answer: ");
    }

    @Override
    public String getAnswer() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
