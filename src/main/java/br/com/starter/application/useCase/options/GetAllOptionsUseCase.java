package br.com.starter.application.useCase.options;

import br.com.starter.domain.option.Option;
import br.com.starter.domain.option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllOptionsUseCase {
    private final OptionService optionService;

    public Page<Option> execute(Pageable pageable) {
        return optionService.getAllOptions(pageable);
    }
}
