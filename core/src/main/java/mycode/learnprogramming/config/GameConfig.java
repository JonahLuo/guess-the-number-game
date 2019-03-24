package mycode.learnprogramming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    // == fields ==
    private int guessCount = 8;
    private int MaxNumber = 75;

    // == Beands Methods ==
    @Bean
    int guessCount(){
        return guessCount;
    }

    @Bean
    int MaxNumber(){
        return MaxNumber;
    }
}
