package ru.otus.tester.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "questions")
public class QuestProps {
    private String file;
    private int successCountPercent;
    @Value("${application.locale}")
    private String locale;

    @SuppressWarnings("unused")
    public void setFile(String file) {
        this.file = file;
    }

    @SuppressWarnings("unused")
    public void setSuccessCountPercent(int successCountPercent) {
        this.successCountPercent = successCountPercent;
    }

    public String getFile() {
        return file + "_" + this.locale + ".csv";
    }

    public int getSuccessCountPercent() {
        return successCountPercent;
    }
}
