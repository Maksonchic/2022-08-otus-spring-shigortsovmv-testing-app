package ru.otus.tester.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import ru.otus.tester.io.StudentVoiceCommunicator;
import ru.otus.tester.io.StudentVoiceCommunicatorImpl;
import ru.otus.tester.controller.Teacher;
import ru.otus.tester.controller.Questions;
import ru.otus.tester.controller.QuestionsService;
import ru.otus.tester.io.TeacherVoice;
import ru.otus.tester.io.TeacherVoiceConsole;
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
    TeacherVoice teacherVoice() {
        return new TeacherVoiceConsole();
    }

    @Bean
    StudentVoiceCommunicator communicator() {
        return new StudentVoiceCommunicatorImpl();
    }

    @Bean
    Teacher teacher(Questions tasks,
                    StudentVoiceCommunicator comm,
                    TeacherVoice voice,
                    @Value("${questions.success-count-percent}") int successPercent) {
            return new Teacher(tasks, comm, voice, successPercent);
    }

}
