package br.com.starter.application.useCase.options;

import br.com.starter.domain.option.Option;
import br.com.starter.domain.option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetOptionsByIsCorrectUseCase {
    private final OptionService optionService;

    public List<Option> execute(boolean isCorrect) {
        return optionService.findByIsCorrect(isCorrect);
    }
}
