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
import ru.otus.tester.config.ConverterService;
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
    @Import(ConverterService.class)
    static class A {}

    @Autowired
    @SuppressWarnings("unused")
    private ApplicationContext context;

    @Autowired
    @SuppressWarnings("unused")
    private ConverterService converterService;

    @DisplayName("Проверка локализации для Англ")
    @Test
    void localizeValEn() {
        ReflectionTestUtils.setField(converterService, "locale", Locale.forLanguageTag("en"));
        assertEquals("en_val1", converterService.localize("test.val1"));
        assertEquals("en_val2", converterService.localize("test.val2"));
    }

    @DisplayName("Проверка локализации для Рус")
    @Test
    void localizeValRu() {
        ReflectionTestUtils.setField(converterService, "locale", Locale.forLanguageTag("ru"));
        assertEquals("ру_вал1", converterService.localize("test.val1"));
        assertEquals("ру_вал2", converterService.localize("test.val2"));
    }

    @DisplayName("Проверка наличия ConverterService бина")
    @Test
    void hasConverterService() {
        assertDoesNotThrow(() -> context.getBean(ConverterService.class));
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

