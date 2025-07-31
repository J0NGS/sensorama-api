package br.com.starter.application.useCase.gamePlayer;

import br.com.starter.domain.gamePlayer.GamePlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetGamePlayersByGameIdUseCase {
    private final GamePlayerService gamePlayerService;
    public List<GamePlayer> execute(UUID gameId) {return gamePlayerService.findByGameId(gameId);}

}
