import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tester.controller.ICommunicator;
import ru.otus.tester.controller.IQuestions;
import ru.otus.tester.controller.Questions;
import ru.otus.tester.model.IQuestion;

import java.io.IOException;

public class Teacher {

    @DisplayName("Single test successful")
    @Test
    void test1_autoAnswer() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        IQuestions test = context.getBean(Questions.class);
        ICommunicator comm = new A();

        try {
            test.init();
            Assertions.assertEquals(ru.otus.tester.controller.Teacher.letsTesting(test, comm), 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class A implements ICommunicator {

        private String rightAnswer;

        @Override
        public void ask(IQuestion task) {
            this.rightAnswer = task.getRight();
        }

        @Override
        public String getAnswer() {
            return this.rightAnswer;
        }
    }

}
