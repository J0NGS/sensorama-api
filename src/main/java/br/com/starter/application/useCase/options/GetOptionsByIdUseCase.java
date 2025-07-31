package br.com.starter.application.useCase.options;

import br.com.starter.domain.option.Option;
import br.com.starter.domain.option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetOptionsByIdUseCase {
    private final OptionService optionService;

    public Option execute(UUID optionId) {
        return optionService.findById(optionId);
    }
}
