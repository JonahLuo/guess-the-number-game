package mycode.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jonah
 * the implmentation of the Game
 */

@Component
public class GameImpl implements Game {

    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);
    private final int guessCount;

    // == fields ==
    private NumberGenerator numberGenerator;
    private int guess;
    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean isValidGuess = true;

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

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    /**
     *
     * @param guess set the guess
     */
    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    /**
     *
     * @return the smallest number
     */
    @Override
    public int getSmallest() {
        return smallest;
    }

    /**
     *
     * @return get the biggest possible number
     */
    @Override
    public int getBiggest() {
        return biggest;
    }

    /**
     *
     * @return get the remaining guess times
     */
    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }


    /**
     * Check if the the guess is valid, nad adjust the range to simplify the difficulty
     * And decrease the number of remaining guesses
     */
    @Override
    public void check() {
        checkTheNumberRange();

        if(isValidGuess){
            if(guess > number) biggest= guess-1;
            if(guess < number) smallest = guess+1;
        }

        remainingGuesses--;
    }

    /**
     *
     * @return true if the guess is valid
     */
    @Override
    public boolean isValidNumberRange() {
        return isValidGuess;
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
        isValidGuess = (guess <= biggest) && (guess >= smallest);
    }
}
