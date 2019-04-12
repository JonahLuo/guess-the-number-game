package mycode.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == fields ==
    private final Game game;

    // == constructor ==
    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == init ==
    @PostConstruct
    public void postConst(){
        log.info("game = {}", game);
    }

    // == public method ==
    @Override
    public String getMainMessage() {
        return "Your guess range is between " + game.getSmallest() +
                " and " + game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessgae() {
        if(game.isGameWon()){
            return "Congrats, YOU WON!! The Number was " + game.getNumber() + "!";
        }else if(game.isGameLost()){
            return "Sorry, You lost. The number was " + game.getNumber() + ". Better Luck Next Time!";
        }else if(!game.isValidNumberRange()){
            return "Invalid Number Range, Please input a valid number! You have " + game.getRemainingGuesses() + " guesses left!";
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess? Your have " + game.getGuessCount() + " chances to guess the number.";
        }else {
            String resultMessage = "Lower";
            if(game.getGuess() < game.getNumber()){
                resultMessage = "Higher";
            }
            return resultMessage + ". You have " + game.getRemainingGuesses() + " guesses left!";
        }
    }
}
