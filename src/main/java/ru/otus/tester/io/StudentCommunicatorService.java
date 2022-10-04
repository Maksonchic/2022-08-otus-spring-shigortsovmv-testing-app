package ru.otus.tester.io;

import java.io.InputStream;
import java.util.Scanner;

public class StudentCommunicatorService implements StudentCommunicator {

    private final InputStream in;

    public StudentCommunicatorService(InputStream in) {
        this.in = in;
    }

    public String getAnswer() {
        Scanner sc = new Scanner(this.in);
        return sc.nextLine();
    }
}
