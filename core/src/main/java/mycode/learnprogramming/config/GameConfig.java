package mycode.learnprogramming.config;

import mycode.learnprogramming.GuessCount;
import mycode.learnprogramming.MaxNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    // == fields ==
    private int guessCount = 8;
    private int maxNumber = 75;

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
