package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetGameByPlayerIdUseCase {
    private final GameService gameService;

    public List<Game> execute(UUID player) {
        return gameService.findGamesByPlayerId(player);
    }
}
