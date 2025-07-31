package br.com.starter.application.useCase.options;

import br.com.starter.domain.option.Option;
import br.com.starter.domain.option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetCorrectOptionsByQuestionIdUseCase {
    private final OptionService optionService;

    public Option execute(UUID questionId) {
        return optionService.findCorrectOptionsByQuestionId(questionId);
    }
}
