package br.com.starter.application.useCase.gamePlayer;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import br.com.starter.domain.gamePlayer.GamePlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllGamePlayersUseCase {
    private final GamePlayerService gamePlayerService;
    public Page<GamePlayer> execute(Pageable pageable) {
        return gamePlayerService.getAllGames(pageable);
    }
}