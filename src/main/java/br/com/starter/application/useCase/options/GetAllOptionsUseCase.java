package br.com.starter.application.useCase.options;

import br.com.starter.domain.Options.Options;
import br.com.starter.domain.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetAllOptionsUseCase {
    private final OptionService optionService;

    public Page<Options> execute(Pageable pageable) {
        return optionService.getAllOptions(pageable);
    }
}
