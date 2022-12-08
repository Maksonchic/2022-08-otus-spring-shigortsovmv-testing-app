package ru.otus.tester.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.tester.config.Localizator;
import ru.otus.tester.controller.QuestionsHandler;
import ru.otus.tester.controller.Teacher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ShellComponent
public class ShellStarter  {

    @Autowired
    private Localizator localizator;

    @Autowired
    private Teacher teacher;

    @Autowired
    private QuestionsHandler questionsHandler;

    private boolean isSaidHello = false;

    @ShellMethod(value = "say hello", key = {"hell", "hello"})
    public String hello() {
        this.isSaidHello = true;
        return this.localizator.localize("shell.hello");
    }

    @ShellMethod(value = "help info", key = "h")
    public String help() {
        return this.getKeysShell().toString();
    }

    @ShellMethod(value = "run app", key = "run")
    @ShellMethodAvailability("isFriendly")
    public void run() {
        this.questionsHandler.init();
        this.teacher.letsTesting();
    }

    private Availability isFriendly() {
        return this.isSaidHello
                ? Availability.available()
                : Availability.unavailable(this.localizator.localize("shell.not-friendly"));
    }

    private List<List<String>> getKeysShell() {
        final List<List<String>> methods = new ArrayList<>();
        Class<?> klass = this.getClass();
        for (final Method method : klass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ShellMethod.class)) {
                ShellMethod annotInstance = method.getAnnotation(ShellMethod.class);
                methods.add(Arrays.stream(annotInstance.key()).toList());
            }
        }
        return methods;
    }
}
