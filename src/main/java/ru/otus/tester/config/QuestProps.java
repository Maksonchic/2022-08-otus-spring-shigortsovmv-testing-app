package ru.otus.tester.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "questions")
public class QuestProps {
    private String file;
    private int successCountPercent;

    @SuppressWarnings("unused")
    public void setFile(String file) {
        this.file = file;
    }

    @SuppressWarnings("unused")
    public void setSuccessCountPercent(int successCountPercent) {
        this.successCountPercent = successCountPercent;
    }

    public String getFile() {
        return file;
    }

    public int getSuccessCountPercent() {
        return successCountPercent;
    }
}
