package ru.otus.tester.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.otus.tester.controller.Teacher;
import ru.otus.tester.controller.QuestionsHandler;
import ru.otus.tester.controller.QuestionsHandlerService;
import ru.otus.tester.controller.ResultCalculator;
import ru.otus.tester.controller.ResultCalculatorService;
import ru.otus.tester.controller.io.TestStudentCommunicatorService;
import ru.otus.tester.io.StudentCommunicator;
import ru.otus.tester.io.TeacherAsker;
import ru.otus.tester.io.TeacherAskerService;
import ru.otus.tester.storage.ResourceProvider;
import ru.otus.tester.storage.SourceReader;
import ru.otus.tester.storage.ResourceProviderImpl;
import ru.otus.tester.storage.SourceReaderService;

import java.io.PrintStream;

@Configuration
@Import({
        ResourceProviderImpl.class,
        TestStudentCommunicatorService.class
})
@PropertySource("config.properties")
public class TestAppConfig {

    @Bean
    TeacherAsker teacherAsker(
            @Value("#{T(System).out}") PrintStream out,
            StudentCommunicator studentCommunicator) {
        return new TeacherAskerService(out, studentCommunicator);
    }

    @Bean(initMethod = "init")
    QuestionsHandler questionsService(ResourceProvider sourceReader) {
        return new QuestionsHandlerService(sourceReader);
    }

    @Bean
    Teacher teacher(QuestionsHandler tasks,
                    TeacherAsker teacherAsker,
                    ResultCalculator resultCalculator) {
        return new Teacher(tasks, teacherAsker, resultCalculator);
    }

}
