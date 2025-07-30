package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.Mode;
import br.com.starter.domain.game.Status;

public class GetGameByModeAndStatusUseCase {
    private final GameService gameService;

    public Game execute(Mode mode, Status status) {
        return gameService.findByModeAndStatus(mode, status);
    }
}
