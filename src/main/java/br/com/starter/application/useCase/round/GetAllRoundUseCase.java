package br.com.starter.application.useCase.round;

import br.com.starter.domain.question.Question;
import br.com.starter.domain.round.Round;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetAllRoundUseCase {
    private final RoundService roundService;

    public Page<Round> execute(Pageable pageable) {
        return roundService.getAllRounds(pageable);
    }
}
