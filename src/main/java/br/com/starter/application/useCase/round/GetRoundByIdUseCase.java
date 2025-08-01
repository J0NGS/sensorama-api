package br.com.starter.application.useCase.round;

import br.com.starter.domain.round.Round;
import lombok.RequiredArgsConstructor;
import br.com.starter.domain.round.RoundService;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetRoundByIdUseCase {
    private final RoundService roundService;

    public Round execute(UUID roundId) {
        return roundService.findById(roundId);
    }
}
