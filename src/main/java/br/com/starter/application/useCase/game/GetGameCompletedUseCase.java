package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;

public class GetGameCompletedUseCase {
    private final GameService gameService;

    public Game execute() {
        return gameService.findCompletedGames();
    }
}
