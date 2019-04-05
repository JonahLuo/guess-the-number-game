package mycode.learnprogramming.controller;

import lombok.extern.slf4j.Slf4j;
import mycode.learnprogramming.service.GameService;
import mycode.learnprogramming.util.AttributeNames;
import mycode.learnprogramming.util.Mappings;
import mycode.learnprogramming.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {
    // == fields ==
    private final GameService gameService;

    // == constructor ==
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // == request handler ==
    @GetMapping(Mappings.PLAY)
    public String play(Model model){
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());

        if(gameService.isGameOver()){
            return ViewNames.GAME_OVER;
        }

        return ViewNames.PLAY;
    }

    @PostMapping(Mappings.PLAY)
    public String playProcess(@RequestParam int guess){
        log.info("guess = {}", guess);
        gameService.checkGuess(guess);
        return Mappings.REDIRECT_PLAY;
    }


}
