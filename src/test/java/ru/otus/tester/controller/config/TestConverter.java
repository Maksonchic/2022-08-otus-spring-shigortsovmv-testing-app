package ru.otus.tester.controller.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;
import ru.otus.tester.config.LocalizatorService;
import ru.otus.tester.config.QuestProps;
import ru.otus.tester.controller.ResultCalculatorService;
import ru.otus.tester.controller.Teacher;
import ru.otus.tester.io.StudentCommunicatorService;
import ru.otus.tester.io.TeacherAskerService;
import ru.otus.tester.storage.ResourceProviderImpl;
import ru.otus.tester.storage.SourceReaderService;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DisplayName("Тестирвание конвертера (пока только локализация и временные игры с контекстом)")
public class TestConverter {

    @Configuration
    @EnableAutoConfiguration
    @Import(LocalizatorService.class)
    static class A {}

    @Autowired
    @SuppressWarnings("unused")
    private ApplicationContext context;

    @Autowired
    @SuppressWarnings("unused")
    private LocalizatorService localizatorService;

    @DisplayName("Проверка локализации для Англ")
    @Test
    void localizeValEn() {
        ReflectionTestUtils.setField(localizatorService, "locale", Locale.forLanguageTag("en"));
        assertEquals("en_val1", localizatorService.localize("test.val1"));
        assertEquals("en_val2", localizatorService.localize("test.val2"));
    }

    @DisplayName("Проверка локализации для Рус")
    @Test
    void localizeValRu() {
        ReflectionTestUtils.setField(localizatorService, "locale", Locale.forLanguageTag("ru"));
        assertEquals("ру_вал1", localizatorService.localize("test.val1"));
        assertEquals("ру_вал2", localizatorService.localize("test.val2"));
    }

    @DisplayName("Проверка наличия ConverterService бина")
    @Test
    void hasConverterService() {
        assertDoesNotThrow(() -> context.getBean(LocalizatorService.class));
    }

    @DisplayName("Проверка наличия прочего контекста")
    @Test
    void hasOther() {
        assertThrows(
                org.springframework.beans.factory.NoSuchBeanDefinitionException.class,
                () -> context.getBean(Teacher.class));
        assertThrows(
                org.springframework.beans.factory.NoSuchBeanDefinitionException.class,
                () -> context.getBean(ResourceProviderImpl.class));
        assertThrows(
                org.springframework.beans.factory.NoSuchBeanDefinitionException.class,
                () -> context.getBean(SourceReaderService.class));
        assertThrows(
                org.springframework.beans.factory.NoSuchBeanDefinitionException.class,
                () -> context.getBean(StudentCommunicatorService.class));
        assertThrows(
                org.springframework.beans.factory.NoSuchBeanDefinitionException.class,
                () -> context.getBean(ResultCalculatorService.class));
        assertThrows(
                org.springframework.beans.factory.NoSuchBeanDefinitionException.class,
                () -> context.getBean(QuestProps.class));
        assertThrows(
                org.springframework.beans.factory.NoSuchBeanDefinitionException.class,
                () -> context.getBean(TeacherAskerService.class));
    }
}

