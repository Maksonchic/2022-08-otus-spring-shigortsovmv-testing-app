package ru.otus.tester.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import ru.otus.tester.controller.QuestionsHandler;
import ru.otus.tester.controller.ResultCalculatorService;
import ru.otus.tester.controller.Teacher;
import ru.otus.tester.controller.QuestionsHandlerService;
import ru.otus.tester.io.StudentCommunicatorService;
import ru.otus.tester.io.TeacherAskerService;
import ru.otus.tester.storage.ResourceProviderImpl;
import ru.otus.tester.storage.SourceReaderService;
import ru.otus.tester.storage.ResourceProvider;

@ConfigurationProperties
@Import({
        ResourceProviderImpl.class,
        SourceReaderService.class,
        StudentCommunicatorService.class,
        Teacher.class,
        ResultCalculatorService.class,
        QuestProps.class,
        ConverterService.class,
        TeacherAskerService.class
})
public class AppConfig {

    @Bean(initMethod = "init")
    QuestionsHandler questionsHandler(ResourceProvider sourceReader) {
        return new QuestionsHandlerService(sourceReader);
    }

}
