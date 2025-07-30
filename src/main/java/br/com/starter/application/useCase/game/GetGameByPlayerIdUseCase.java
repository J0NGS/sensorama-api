package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;

import java.util.UUID;

public class GetGameByPlayerIdUseCase {
    private final GameService gameService;

    public Game execute(UUID player) {
        return gameService.findGamesByPlayerId(player);
    }
}
