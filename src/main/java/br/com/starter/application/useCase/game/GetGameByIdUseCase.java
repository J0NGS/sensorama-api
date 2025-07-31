package br.com.starter.application.useCase.game;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetGameByIdUseCase {
    private final GameService gameService;

    public Game execute(UUID gameId) {
        return gameService.findGameById(gameId);
    }
}
