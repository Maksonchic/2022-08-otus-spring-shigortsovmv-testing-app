package ru.otus.tester.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.tester.controller.*;
import ru.otus.tester.controller.io.TestStudentCommunicatorService;
import ru.otus.tester.controller.io.TestTeacherAskerService;
import ru.otus.tester.io.StudentCommunicator;
import ru.otus.tester.io.TeacherAsker;
import ru.otus.tester.storage.ResourceProvider;
import ru.otus.tester.storage.SourceReader;
import ru.otus.tester.storage.ResourceProviderImpl;
import ru.otus.tester.storage.SourceReaderService;

@Configuration
@PropertySource("config.properties")
public class TestAppConfig {

    @Bean
    SourceReader sourceReader(
            @Value("${questions.file}") String fileName) {
        return new SourceReaderService(fileName);
    }

    @Bean
    ResourceProvider resourceProvider(SourceReader sourceReader) {
        return new ResourceProviderImpl(sourceReader);
    }

    @Bean(initMethod = "init")
    QuestionsHandler questionsService(ResourceProvider sourceReader) {
        return new QuestionsHandlerService(sourceReader);
    }

    @Bean
    TeacherAsker teacherAsker() {
        return new TestTeacherAskerService();
    }

    @Bean
    StudentCommunicator communicator() {
        return new TestStudentCommunicatorService();
    }

    @Bean
    Teacher teacher(QuestionsHandler tasks,
                    StudentCommunicator studentCommunicator,
                    TeacherAsker teacherAsker,
                    @Value("${questions.success-count-percent}") int successPercent) {
        return new Teacher(tasks, teacherAsker, successPercent);
    }

}
