package ru.otus.tester.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import ru.otus.tester.controller.QuestionsHandler;
import ru.otus.tester.controller.ResultCalculator;
import ru.otus.tester.controller.ResultCalculatorService;
import ru.otus.tester.controller.Teacher;
import ru.otus.tester.controller.QuestionsHandlerService;
import ru.otus.tester.io.StudentCommunicatorService;
import ru.otus.tester.io.TeacherAskerService;
import ru.otus.tester.storage.ResourceProviderImpl;
import ru.otus.tester.storage.SourceReaderService;
import ru.otus.tester.storage.ResourceProvider;

@Configuration
@Import({
        ResourceProviderImpl.class,
        SourceReaderService.class,
        StudentCommunicatorService.class,
        Teacher.class,
        TeacherAskerService.class
})
@PropertySource("config.properties")
public class AppConfig {

    @Bean
    ResultCalculator resultCalculator(
            @Value("${questions.success-count-percent}") int successPercent,
            QuestionsHandler questionsHandler) {
        return new ResultCalculatorService(successPercent, questionsHandler.getTasksCount());
    }

    @Bean(initMethod = "init")
    QuestionsHandler questionsHandler(ResourceProvider sourceReader) {
        return new QuestionsHandlerService(sourceReader);
    }

}
