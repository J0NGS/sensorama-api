package br.com.starter.application.useCase.game;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import br.com.starter.domain.game.Mode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
// é necessário? acho que só se for ter um histórico e servir como filtro
public class GetGameByModeUseCase {
    private final GameService gameService;
    public Game execute(Mode mode) {
        return gameService.findByMode(mode);
    }

}
