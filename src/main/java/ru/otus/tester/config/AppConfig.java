package ru.otus.tester.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import ru.otus.tester.controller.StudentCommunicator;
import ru.otus.tester.controller.StudentCommunicatorImpl;
import ru.otus.tester.controller.Teacher;
import ru.otus.tester.controller.Questions;
import ru.otus.tester.controller.QuestionsService;
import ru.otus.tester.storage.SourceReader;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    SourceReader sourceReader() {
        return new SourceReader();
    }

    @Bean(initMethod = "init")
    Questions questionsService() {
        return new QuestionsService();
    }

    @Bean
    StudentCommunicator communicator() {
        return new StudentCommunicatorImpl();
    }

    @Bean
    Teacher teacher(Questions tasks, StudentCommunicator comm) {
        return new Teacher(tasks, comm);
    }

}
