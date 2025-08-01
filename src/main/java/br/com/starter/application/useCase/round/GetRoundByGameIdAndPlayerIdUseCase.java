package br.com.starter.application.useCase.round;

import br.com.starter.domain.question.Question;
import br.com.starter.domain.round.Round;
import br.com.starter.domain.round.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetRoundByGameIdAndPlayerIdUseCase {
    private final RoundService roundService;

    public List<Round> execute(UUID gameId, UUID playerId) {
        return roundService.findByGameIdAndPlayerId(gameId, playerId);
    }
}
