package mycode.learnprogramming.console;

import lombok.extern.slf4j.Slf4j;
import mycode.learnprogramming.config.GameConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {

    public static void main(String[] args) {
        //create context(container) with annotation configuration
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        //close context(container)
        context.close();
    }
}
