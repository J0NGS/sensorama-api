package br.com.starter.application.useCase.game;

import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DeleteGameUseCase {
    private final GameService gameService;

    public boolean execute(UUID gameId) {
        if (gameService.deleteGameById(gameId)) {
            return true;
        }
        throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Não foi possível deletá-lo.");
    }
}
