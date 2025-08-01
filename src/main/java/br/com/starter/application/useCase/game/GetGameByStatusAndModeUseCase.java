package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import br.com.starter.domain.game.Mode;
import br.com.starter.domain.game.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
 // se esse UseCase for para achar partida online, pode remover pois o createGameOnline já faz isso
public class GetGameByStatusAndModeUseCase {
    private final GameService gameService;

    public List<Game> execute(Status status, Mode mode) {
        return gameService.findByStatusAndMode(status, mode);
    }
}
