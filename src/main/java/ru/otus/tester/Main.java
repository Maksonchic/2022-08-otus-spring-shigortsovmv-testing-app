package ru.otus.tester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.tester.config.AppConfig;
import ru.otus.tester.controller.Teacher;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        Teacher teacher = context.getBean(Teacher.class);

        teacher.letsTesting();
    }
}
