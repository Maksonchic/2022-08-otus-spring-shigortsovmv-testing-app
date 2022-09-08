package ru.otus.tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tester.controller.Teacher;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Teacher teacher = context.getBean(Teacher.class);

        teacher.letsTesting();
    }
}
