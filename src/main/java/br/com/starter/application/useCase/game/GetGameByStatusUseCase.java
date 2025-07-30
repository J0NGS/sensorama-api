package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.Status;

public class GetGameByStatusUseCase {
    private final GameService gameService;
    public Game execute(Status status) {
        return gameService.findByStatus(status);
    }

}
