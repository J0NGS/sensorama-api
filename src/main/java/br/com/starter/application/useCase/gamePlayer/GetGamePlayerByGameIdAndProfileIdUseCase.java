package br.com.starter.application.useCase.gamePlayer;

import br.com.starter.domain.gamePlayer.GamePlayer;

import java.util.List;
import java.util.UUID;

public class GetGamePlayerByGameIdAndProfileIdUseCase {
    private final GamePlayerService gamePlayerService;
    public List<GamePlayer> execute(UUID gameId, UUID profileId) {return gamePlayerService.findByGameIdAndProfileId(gameId, profileId);}
}
