package br.com.starter.application.useCase.game;


import br.com.starter.application.api.game.dto.GameRegistrationRequest;
import br.com.starter.domain.game.Game;

public class CreateGameUseCase {
    private final GameService gameService;

    public Game execute(GameRegistrationRequest request) {


        Game game = new Game();
        game.setMode(request.mode());
        game.setStatus(request.status());
        game.setStatus(request.status());
        game.setPlayers(request.players());
        game.setStartTime(request.startTime());
        game.setEndTime(request.endTime());

        return gameService.createGame(game);

    }
}
