package ru.otus.tester.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.Locale;

public class ConverterService implements Converter {

    private final MessageSource messageSource;
    private final Locale locale;

    public ConverterService(
            MessageSource messageSource,
            @Value("${application.locale}") String locale) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(locale);
    }

    @Override
    public String[] localize(String[] strings) {
        return Arrays
                .stream(strings)
                .map(code -> messageSource.getMessage(code, new String[]{}, this.locale))
                .toArray(String[]::new);
    }

    @Override
    public String localize(String code) {
        return messageSource.getMessage(code, new String[]{}, this.locale);
    }

    @Override
    public String localize(String code, Object... args) {
        return messageSource.getMessage(code, args, this.locale);
    }
}
