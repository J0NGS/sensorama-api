package br.com.starter.application.useCase.game;

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
public class CreateGameOnlineUseCase {
    private final GameService gameService;

    private final ProfileService profileService;

    public Game execute(UUID playerId) {
        Profile player = profileService.findById(playerId);
        if(player != null){
            return gameService.createGameOnline(player);
        }
        throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Player nao encontrado");
    }
}
