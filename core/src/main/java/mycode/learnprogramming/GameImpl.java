package mycode.learnprogramming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jonah
 * the implmentation of the Game
 */

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    // == fields ==
    private final int guessCount;

    @Getter(AccessLevel.NONE)
    private NumberGenerator numberGenerator;

    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;

    // == constructor ==
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == init ==
    /**
     * reset all the fields, run before starting of the game
     */
    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        guess = numberGenerator.getMinNumber();
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("The number is = {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("It is preDestory()");
    }

    /**
     * Check if the the guess is valid, nad adjust the range to simplify the difficulty
     * And decrease the number of remaining guesses
     */
    @Override
    public void check() {
        checkTheNumberRange();

        if(validNumberRange){
            if(guess > number) biggest= guess-1;
            if(guess < number) smallest = guess+1;
            remainingGuesses--;
        }
    }

    /**
     *
     * @return true if player won the game
     */
    @Override
    public boolean isGameWon() {
        return number == guess;
    }

    /**
     *
     * @return true if player lost the game
     */
    @Override
    public boolean isGameLost() {
        return remainingGuesses <= 0 && !isGameWon();
    }


    // == private methods ==

    /**
     * to check if the guess is within the biggest and smallest numbers
     */
    private void checkTheNumberRange(){
        validNumberRange = (guess <= biggest) && (guess >= smallest);
    }
}
