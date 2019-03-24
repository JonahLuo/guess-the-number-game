package mycode.learnprogramming.console;

import mycode.learnprogramming.config.AppConfig;
import mycode.learnprogramming.MessageGenerator;
import mycode.learnprogramming.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Hello form core");

        //create context(container) with annotation configuration
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //get numberGenerator from context (container)
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        //call method from NumberGenerator class
        int temp = numberGenerator.next();

        //log the number to the console
        log.info("number = {}", temp);

        //get the messageGenerator from context (container)
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        //use the method from MessageGenerator
        log.info(messageGenerator.getMainMessage());
        log.info(messageGenerator.getResultMessgae());

        //close context(container)
        context.close();
    }
}
