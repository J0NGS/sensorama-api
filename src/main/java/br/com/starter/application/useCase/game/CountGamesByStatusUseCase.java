package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.GameService;
import br.com.starter.domain.game.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CountGamesByStatusUseCase {
    private final GameService gameService;

    public Long execute(Status status) {
        return gameService.countByStatus(status);
    }
}
