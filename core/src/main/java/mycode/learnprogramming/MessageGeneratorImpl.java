package mycode.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {

    private final static Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // == fields ==
    @Autowired
    private Game game;
    private int guessCount = 10;

    // == init ==
    @PostConstruct
    public void postConst(){
        log.info("game = {}", game);
    }

    // == public method ==
    @Override
    public String getMainMessage() {
        return "Your guess range is between " + game.getBiggest() +
                " and " + game.getSmallest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessgae() {
        if(game.isGameWon()){
            return "Congrats, YOU WON!!";
        }else if(game.isGameLost()){
            return "Sorry, You lost. The number was " + game.getNumber() + ". Better Luck Next Time!";
        }else if(!game.isValidNumberRange()){
            return "Invalid Number Range, Please input a valid number!";
        }else if(game.getRemainingGuesses() == guessCount){
            return "What is your first guess?";
        }else {
            String resultMessage = "Lower";
            if(game.getGuess() < game.getNumber()){
                resultMessage = "Higher";
            }
            return resultMessage + ". You have " + game.getRemainingGuesses() + " guess left!";
        }
    }
}
