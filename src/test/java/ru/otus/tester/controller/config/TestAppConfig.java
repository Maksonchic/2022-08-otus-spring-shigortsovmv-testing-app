package ru.otus.tester.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.tester.controller.*;
import ru.otus.tester.controller.io.TestStudentVoiceCommunicator;
import ru.otus.tester.controller.io.TestTeacherVoice;
import ru.otus.tester.io.StudentVoiceCommunicator;
import ru.otus.tester.io.TeacherVoice;
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
        return new TestTeacherVoice();
    }

    @Bean
    StudentVoiceCommunicator communicator() {
        return new TestStudentVoiceCommunicator();
    }

    @Bean
    Teacher teacher(
            Questions tasks,
            StudentVoiceCommunicator comm,
            TeacherVoice voice,
            @Value("${questions.success-count-percent}") int successPercent) {
        return new Teacher(tasks, comm, voice, successPercent);
    }

}
