package ru.otus.tester;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.tester.config.AppConfig;
import ru.otus.tester.controller.Teacher;

@ComponentScan
public class Application {
    public void run() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Teacher teacher = context.getBean(Teacher.class);

        teacher.letsTesting();
    }
}
