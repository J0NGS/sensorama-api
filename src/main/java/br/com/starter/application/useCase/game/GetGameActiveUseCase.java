package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;

public class GetGameActiveUseCase {
    private final GameService gameService;

    public Game execute() {
        return gameService.findActiveGames();
    }
}
