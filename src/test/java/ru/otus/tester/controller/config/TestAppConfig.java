package ru.otus.tester.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.tester.controller.*;
import ru.otus.tester.io.StudentCommunicator;
import ru.otus.tester.io.TeacherVoice;
import ru.otus.tester.io.TeacherVoiceConsole;
import ru.otus.tester.storage.SourceReader;

@Configuration
@PropertySource("application.properties")
public class TestAppConfig {

    @Bean
    SourceReader sourceReader() {
        return new SourceReader();
    }

    @Bean(initMethod = "init")
    Questions questionsService() {
        return new QuestionsService();
    }

    @Bean
    TeacherVoice teacherVoice() {
        return new TeacherVoiceConsole();
    }

    @Bean
    StudentCommunicator communicator() {
        return new TestCommunicator();
    }

    @Bean
    Teacher teacher(
            Questions tasks,
            StudentCommunicator comm,
            TeacherVoice voice,
            @Value("${questions.success-count-percent}") int successPercent) {
        return new Teacher(tasks, comm, voice, successPercent);
    }

}
