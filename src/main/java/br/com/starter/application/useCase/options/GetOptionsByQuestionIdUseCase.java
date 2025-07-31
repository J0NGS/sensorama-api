package br.com.starter.application.useCase.options;

import br.com.starter.domain.option.Option;
import br.com.starter.domain.option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetOptionsByQuestionIdUseCase {
    private final OptionService optionService;

    public List<Option> execute(UUID questionId) {
        return optionService.findByQuestionId(questionId);
    }
}
