package br.com.starter.application.useCase.game;

import br.com.starter.application.api.game.dto.UpdateGameDTO;
import br.com.starter.domain.category.Category;

import java.util.UUID;

public class UpdateGameUseCase {
    private final GameService gameService;

    public Category execute(UUID gameId, UpdateGameDTO updateGameDTO) {
        return gameService.updateGame(gameId, updateGameDTO);
    }
}
