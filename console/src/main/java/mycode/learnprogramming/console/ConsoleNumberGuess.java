package mycode.learnprogramming.console;

import lombok.extern.slf4j.Slf4j;
import mycode.learnprogramming.Game;
import mycode.learnprogramming.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleNumberGuess {
    // == fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    // == constructor ==
    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

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
