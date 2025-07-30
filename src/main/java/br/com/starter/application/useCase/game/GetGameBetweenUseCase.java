package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;

import java.time.LocalDateTime;

public class GetGameBetweenUseCase {
    private final GameService gameService;

    public Game execute(LocalDateTime start, LocalDateTime end) {
        return gameService.findByStartTimeBetween(start,end);
    }

}
