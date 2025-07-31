package br.com.starter.application.useCase.options;

import br.com.starter.domain.Options.Options;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetOptionCorrectByQuestionIdUseCase {
    private final OptionService optionService;

    public List<Options> execute(UUID questionId) {
        return gameService.findCorrectOptionsByQuestionId(questionId);
    }

}
