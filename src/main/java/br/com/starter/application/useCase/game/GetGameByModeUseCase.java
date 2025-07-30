package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.Mode;

public class GetGameByModeUseCase {
    private final GameService gameService;
    public Game execute(Mode mode) {
        return gameService.findByMode(mode);
    }

}
