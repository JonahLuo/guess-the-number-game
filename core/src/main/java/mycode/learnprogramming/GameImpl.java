package mycode.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jonah
 * the implmentation of the Game
 */

public class GameImpl implements Game {

    /**
     * return a log for this class
     */
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // --fields--
    @Autowired
    private NumberGenerator numberGenerator;

    @Autowired
    @GuessCount
    private int guessCount;
    private int guess;
    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean isValidGuess = true;

    /*
    This constructor is for the Constructor based Dependency Injection method
    // -- constructors --
    public GameImpl(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }
    */

    // == init ==

    /**
     * reset all the fields, run before starting of the game
     */
    @PostConstruct
    @Override
    public void reset() {
        smallest = 0;
        remainingGuesses = guessCount;
        guess = 0;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("The number is = {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("It is preDestory()");
    }


    // --public methods--

    /*
    public void setNumberGenerator(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }
    */

    /**
     *
     * @return the number that players are guessing
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     *
     * @return the guess player made last time
     */
    @Override
    public int getGuess() {
        return guess;
    }

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


    // -- private methods --

    /**
     * to check if the guess is within the biggest and smallest numbers
     */
    private void checkTheNumberRange(){
        isValidGuess = (guess <= biggest) && (guess >= smallest);
    }
}
