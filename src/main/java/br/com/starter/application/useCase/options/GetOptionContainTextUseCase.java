package br.com.starter.application.useCase.options;

import br.com.starter.domain.Options.Options;

import java.util.List;
import java.util.UUID;

public class GetOptionContainTextUseCase {
    private final OptionService optionService;

    public List<Options> execute(String text) {
        return gameService.findByTextContaining(text);
    }

}
