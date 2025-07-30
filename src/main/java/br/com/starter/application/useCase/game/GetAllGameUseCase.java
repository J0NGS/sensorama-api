package br.com.starter.application.useCase.game;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetAllGameUseCase {
    private final GameService gameService;

    public Page<Game> execute(Pageable pageable) {
        return gameService.getAllGames(pageable);
    }
}
