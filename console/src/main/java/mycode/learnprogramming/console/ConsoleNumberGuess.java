package mycode.learnprogramming.console;

import mycode.learnprogramming.Game;
import mycode.learnprogramming.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess {
    // == constants ==
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    // == fields ==
    @Autowired
    private Game game;

    @Autowired
    private MessageGenerator messageGenerator;


    //== event ==
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() --> This is Console Number Guess Class");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessgae());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if(game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessgae());
                System.out.println("Play again? (y/n)");

                String PlayAgain = scanner.nextLine().trim();
                if(!PlayAgain.equalsIgnoreCase("y")) break;

                game.reset();
            }


        }
    }
}
