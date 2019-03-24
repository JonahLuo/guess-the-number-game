package mycode.learnprogramming.config;

import mycode.learnprogramming.GuessCount;
import mycode.learnprogramming.MaxNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
public class GameConfig {
    // == fields ==
    @Value("${game.guessCount:999}")
    private int guessCount;

    @Value("${game.maxNumber:999}")
    private int maxNumber;

    // == Beands Methods ==
    @Bean
    @GuessCount
    int myGuessCount(){
        return guessCount;
    }

    @Bean
    @MaxNumber
    int myMaxNumber(){
        return maxNumber;
    }
}
