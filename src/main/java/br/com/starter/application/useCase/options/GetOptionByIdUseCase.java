package br.com.starter.application.useCase.options;

import br.com.starter.domain.game.Game;
import br.com.starter.domain.Options.Options;

import java.util.UUID;

public class GetOptionByIdUseCase {
    private final OptionService optionService;

    public Options execute(UUID optionId) {
        return gameService.findOptionById(optionId);
    }

}
