package br.com.starter.application.useCase.gamePlayer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import br.com.starter.domain.gamePlayer.GamePlayerService;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetGamePlayersAvarageScorebyGameIdUseCase {
    private final GamePlayerService gamePlayerService;
    public Double execute(UUID gameId) {return gamePlayerService.getAverageScoreByGameId(gameId);}

}
