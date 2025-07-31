package br.com.starter.application.useCase.game;

import br.com.starter.application.api.game.dto.UpdateGameDTO;
import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UpdateGameUseCase {
    private final GameService gameService;

    public Game execute(UUID gameId, UpdateGameDTO updateGameDTO) {
        return gameService.updateGame(gameId, updateGameDTO);
    }
}
