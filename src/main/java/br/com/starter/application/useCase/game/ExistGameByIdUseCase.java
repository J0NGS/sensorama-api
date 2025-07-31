package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ExistGameByIdUseCase {
    private final GameService gameService;

    public boolean execute(UUID gameId) {
        return gameService.existsById(gameId);
    }
}
