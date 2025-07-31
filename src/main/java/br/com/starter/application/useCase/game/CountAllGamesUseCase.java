package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class CountAllGamesUseCase {
    private final GameService gameService;

    public Long execute() {
        return gameService.countAllGames();
    }
}
