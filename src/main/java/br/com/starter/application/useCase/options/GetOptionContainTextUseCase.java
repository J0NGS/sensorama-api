package br.com.starter.application.useCase.options;

import br.com.starter.domain.Options.Options;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetOptionContainTextUseCase {
    private final OptionService optionService;

    public List<Options> execute(String text) {
        return gameService.findByTextContaining(text);
    }

}
