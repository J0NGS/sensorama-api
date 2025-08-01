package br.com.starter.application.useCase.game;


import br.com.starter.application.api.game.dto.GameRegistrationRequest;
import br.com.starter.domain.game.Game;
import br.com.starter.domain.game.GameService;
import br.com.starter.domain.profile.Profile;
import br.com.starter.domain.profile.ProfileService;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
// Refatorar, create game no service é para criar uma partida offline
// tem a função createGameOnline, que busca uma partida online e caso não exista, cria uma
// todas a lógicas para iniciar a partida estão no service, de uma olhada


public class CreateGameUseCase {
    private final GameService gameService;
    private final ProfileService profileService;

    public Game execute(UUID playerId) {
        Profile player = profileService.findById(playerId);
        if(player != null){
            return gameService.createGame(player);
        }
        throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Player nao encontrado");
    }
}
