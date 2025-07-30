package br.com.starter.application.useCase.game;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.game.Game;

import java.util.UUID;

public class GetGameByIdUseCase {
    private final GameService gameService;

    public Game execute(UUID gameId) {
        return gameService.findGameById(gameId);
    }
}
