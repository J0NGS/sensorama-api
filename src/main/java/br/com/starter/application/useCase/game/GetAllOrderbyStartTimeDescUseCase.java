package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import br.com.starter.domain.game.Mode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetAllOrderbyStartTimeDescUseCase {
    private final GameService gameService;

    public List<Game> execute() {
        return gameService.findAllOrderByStartTimeDesc();
    }
}
