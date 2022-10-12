package ru.otus.tester.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Scanner;

@Service
public class StudentCommunicatorService implements StudentCommunicator {

    private final InputStream in;

    public StudentCommunicatorService(@Value("#{T(java.lang.System).in}") InputStream in) {
        this.in = in;
    }

    @Override
    public String getAnswer() {
        Scanner sc = new Scanner(this.in);
        return sc.nextLine();
    }
}
