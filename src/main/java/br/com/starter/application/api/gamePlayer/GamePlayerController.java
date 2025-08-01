package br.com.starter.application.api.gamePlayer;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.gamePlayer.*;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequestMapping("/sensorama/api/gamePlayer")
@RequiredArgsConstructor
public class GamePlayerController {
    private final GetAllGamePlayersUseCase getAllGamePlayersUseCase;
    private final GetGamePlayersByGameIdUseCase getGamePlayersByGameIdUseCase;
    private final GetGamePlayersByProfileIdUseCase getGamePlayersByProfileIdUseCase;
    private final GetGamePlayerByGameIdAndProfileIdUseCase getGamePlayerByGameIdAndProfileIdUseCase;
    private final GetGamePlayersByCurrentPlayerUseCase getGamePlayersByCurrentPlayerUseCase;
    private final GetGamePlayersByCurrentPlayerUseCase getGamePlayerByCurrentPlayerUseCase;
    //index
    @GetMapping
    public ResponseEntity<?> gamePlayer(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllGamePlayersUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //findBy gameId
    @GetMapping("/Game/{gameId}")
    public ResponseEntity<?> findByGame(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("gameId") UUID gameId) {
        ResponseDTO<?> response = new ResponseDTO<>(getGamePlayersByGameIdUseCase.execute(gameId));
        return ResponseEntity.ok(response);
    }

    //findBy profile
    @GetMapping("/Profile/{profileId}")
    public ResponseEntity<?> findByProfile(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("profileId") UUID profileId) {
        ResponseDTO<?> response = new ResponseDTO<>(getGamePlayersByProfileIdUseCase.execute(profileId));
        return ResponseEntity.ok(response);
    }
    //findBy profile game
    @GetMapping("/Game/{gameId}/Profile/{profileId}")
    public ResponseEntity<?> findByGameProfile(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("gameId") UUID gameId,
                                              @PathVariable("profileId") UUID profileId) {
        ResponseDTO<?> response = new ResponseDTO<>(getGamePlayerByGameIdAndProfileIdUseCase.execute(gameId,profileId));
        return ResponseEntity.ok(response);
    }

    //findBy current user
    @GetMapping("/user/{profileId}")
    public ResponseEntity<?> finByCurrentUser(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("profileId") UUID profileId) {
        ResponseDTO<?> response = new ResponseDTO<>(getGamePlayersByCurrentPlayerUseCase.execute(profileId));
        return ResponseEntity.ok(response);
    }

    //avarage score
    @GetMapping("/Score/{gameId}")
    public ResponseEntity<?> avarageScore(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("gameId") UUID gameId) {
        ResponseDTO<?> response = new ResponseDTO<>(getGamePlayerByCurrentPlayerUseCase.execute(gameId));
        return ResponseEntity.ok(response);
    }
}
