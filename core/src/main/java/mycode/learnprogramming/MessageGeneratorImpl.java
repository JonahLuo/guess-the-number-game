package mycode.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constant ==
    private static final String MAIN_MESSAGE= "game.message.main";
    private static final String WIN = "game.message.win";
    private static final String LOST = "game.message.lost";
    private static final String INVALIDRANGE = "game.message.invalidRange";
    private static final String FIRSTGUESS = "game.message.firstGuess";
    private static final String HIGHER = "game.message.higher";
    private static final String LOWER = "game.message.lower";
    private static final String REMAININGGUESSES = "game.message.remainingGuesses";

    // == fields ==
    private final Game game;
    private final MessageSource messageSource;

    // == constructor ==
    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == init ==
    @PostConstruct
    public void postConst(){
        log.info("game = {}", game);
    }

    // == public method ==
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());

        //return "Your guess range is between " + game.getSmallest() +
        //       " and " + game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessgae() {
        if(game.isGameWon()){
            return getMessage(WIN, game.getNumber());
            //return "Congrats, YOU WON!! The Number was " + game.getNumber() + "!";
        }else if(game.isGameLost()){
            return getMessage(LOST, game.getNumber());
            //return "Sorry, You lost. The number was " + game.getNumber() + ". Better Luck Next Time!";
        }else if(!game.isValidNumberRange()){
            return getMessage(INVALIDRANGE) + getMessage(REMAININGGUESSES,game.getRemainingGuesses());
            //return "Invalid Number Range, Please input a valid number! You have " + game.getRemainingGuesses() + " guesses left!";
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return getMessage(FIRSTGUESS) + getMessage(REMAININGGUESSES, game.getRemainingGuesses());
            //return "What is your first guess? Your have " + game.getGuessCount() + " chances to guess the number.";
        }else {
            String resultMessage = getMessage(LOWER);
            if(game.getGuess() < game.getNumber()){
                resultMessage = getMessage(HIGHER);
            }
            return resultMessage+getMessage(REMAININGGUESSES, game.getRemainingGuesses());
        }
    }

    // == private methods ==
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
