package br.com.starter.application.useCase.round;

import br.com.starter.domain.question.Question;
import br.com.starter.domain.round.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllRoundUseCase {
    private final RoundService roundService;

    public Page<Round> execute(Pageable pageable) {
        return roundService.getAllRounds(pageable);
    }
}
