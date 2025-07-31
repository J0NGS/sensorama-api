package br.com.starter.application.useCase.game;


import br.com.starter.application.api.game.dto.GameRegistrationRequest;
import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
// Refatorar, create game no service é para criar uma partida offline
// tem a função createGameOnline, que busca uma partida online e caso não exista, cria uma
// todas a lógicas para iniciar a partida estão no service, de uma olhada
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
