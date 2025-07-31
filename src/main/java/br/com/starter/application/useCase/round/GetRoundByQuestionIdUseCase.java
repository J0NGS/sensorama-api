package br.com.starter.application.useCase.round;

import br.com.starter.domain.round.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetRoundByQuestionIdUseCase {
    private final RoundService roundService;

    public List<Round> execute(UUID questionId) {
        return roundService.findOptionByQuestionId(questionId);
    }

}
