package ru.otus.tester.config;

public interface Converter {
    String[] localize(String[] strings);
    String localize(String code);
    String localize(String code, Object... args);
}
