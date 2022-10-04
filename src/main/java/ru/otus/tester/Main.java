package ru.otus.tester;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.tester.config.AppConfig;
import ru.otus.tester.controller.Teacher;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Teacher teacher = context.getBean(Teacher.class);

        teacher.letsTesting();
    }
}
