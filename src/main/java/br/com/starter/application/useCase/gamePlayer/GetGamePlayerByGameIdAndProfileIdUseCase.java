package br.com.starter.application.useCase.gamePlayer;

import br.com.starter.domain.gamePlayer.GamePlayer;
import br.com.starter.domain.gamePlayer.GamePlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetGamePlayerByGameIdAndProfileIdUseCase {
    private final GamePlayerService gamePlayerService;
    public GamePlayer execute(UUID gameId, UUID profileId) {return gamePlayerService.findByGameIdAndProfileId(gameId, profileId);}
}
