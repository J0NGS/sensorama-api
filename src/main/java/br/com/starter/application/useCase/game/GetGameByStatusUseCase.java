package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import br.com.starter.domain.game.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class GetGameByStatusUseCase {
    private final GameService gameService;

    public List<Game> execute(Status status) {
        return gameService.findByStatus(status);
    }
}
