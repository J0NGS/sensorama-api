package br.com.starter.application.useCase.round;

import br.com.starter.domain.round.Round;

import java.util.List;
import java.util.UUID;

public class GetRoundByPlayerIdUseCase {
    private final RoundService roundService;

    public List<Round> execute(UUID playerId) {
        return roundService.findByPlayerId(playerId);
    }

}
