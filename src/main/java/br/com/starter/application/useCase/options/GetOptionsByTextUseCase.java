package br.com.starter.application.useCase.options;

import br.com.starter.domain.option.Option;
import br.com.starter.domain.option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetOptionsByTextUseCase {
    private final OptionService optionService;

    public List<Option> execute(String text) {
        return optionService.findByTextContainingIgnoreCase(text);
    }
}
