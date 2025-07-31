package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import br.com.starter.domain.game.Mode;
import br.com.starter.domain.game.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
 // se esse UseCase for para achar partida online, pode remover pois o createGameOnline já faz isso
public class GetGameByModeAndStatusUseCase {
    private final GameService gameService;

    public Game execute(Mode mode, Status status) {
        return gameService.findByModeAndStatus(mode, status);
    }
}
