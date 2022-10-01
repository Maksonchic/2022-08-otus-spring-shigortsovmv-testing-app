package ru.otus.tester.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import ru.otus.tester.controller.*;
import ru.otus.tester.io.StudentCommunicator;
import ru.otus.tester.io.StudentCommunicatorService;
import ru.otus.tester.io.TeacherAsker;
import ru.otus.tester.io.TeacherAskerService;
import ru.otus.tester.storage.ResourceProviderImpl;
import ru.otus.tester.storage.SourceReader;
import ru.otus.tester.storage.SourceReaderService;
import ru.otus.tester.storage.ResourceProvider;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@Import(ResourceProviderImpl.class)
@PropertySource("config.properties")
public class AppConfig {

    @Bean
    ResultCalculator resultCalculator(
            @Value("${questions.success-count-percent}") int successPercent,
            QuestionsHandler questionsHandler) {
        return new ResultCalculatorService(successPercent, questionsHandler.getTasksCount());
    }

    @Bean
    SourceReader sourceReader(
            @Value("${questions.file}") String fileName) {
        return new SourceReaderService(fileName);
    }

    @Bean(initMethod = "init")
    QuestionsHandler questionsHandler(ResourceProvider sourceReader) {
        return new QuestionsHandlerService(sourceReader);
    }

    @Bean
    TeacherAsker teacherAsker(
            @Value("#{T(java.lang.System).out}") PrintStream out,
            StudentCommunicator studentCommunicator) {
        return new TeacherAskerService(out, studentCommunicator);
    }

    @Bean
    StudentCommunicator studentCommunicator(
            @Value("#{T(java.lang.System).in}") InputStream in) {
        return new StudentCommunicatorService(in);
    }

    @Bean
    Teacher teacher(QuestionsHandler tasks,
                    TeacherAsker teacherAsker,
                    ResultCalculator resultCalculator) {
            return new Teacher(tasks, teacherAsker, resultCalculator);
    }

}
