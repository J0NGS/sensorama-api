package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class GetGameBetweenUseCase {
    private final GameService gameService;

    public Game execute(LocalDateTime start, LocalDateTime end) {
        return gameService.findByStartTimeBetween(start,end);
    }

}
