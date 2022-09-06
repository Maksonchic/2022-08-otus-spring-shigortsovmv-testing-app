package ru.otus.tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tester.controller.Communicator;
import ru.otus.tester.controller.ICommunicator;
import ru.otus.tester.controller.Questions;
import ru.otus.tester.controller.Teacher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Questions test = context.getBean(Questions.class);
        ICommunicator comm = context.getBean(Communicator.class);

        try {
            test.init();
            Teacher.letsTesting(test, comm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
