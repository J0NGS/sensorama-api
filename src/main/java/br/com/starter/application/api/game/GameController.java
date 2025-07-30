package br.com.starter.application.api.game;


import br.com.starter.application.api.category.dto.CategoryRegistrationRequest;
import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.api.game.dto.GameRegistrationRequest;
import br.com.starter.application.api.game.dto.UpdateGameDTO;
import br.com.starter.application.useCase.game.*;
import br.com.starter.domain.game.Mode;
import br.com.starter.domain.game.Status;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/game")
@RequiredArgsConstructor
public class GameController {
    private final GetGameByIdUseCase getGameByIdUseCase;
    private final GetAllGameUseCase getAllGameUseCase;

    private final GetGameActiveUseCase getGameActiveUseCase;
    private final GetGameCompletedUseCase getGameCompletedUseCase;
    private final GetGameByPlayerIdUseCase getGameByPlayerIdUseCase;
    private final GetGameByModeUseCase getGameByModeUseCase;
    private final GetGameByStatusUseCase getGameByStatusUseCase;
    private final GetGameByModeAndStatusUseCase getGameByModeAndStatusUseCase;
    private final GetGameBetweenUseCase getGameBetweenUseCase;

    private final CreateGameUseCase createGameUseCase;
    private final UpdateGameUseCase updateGameUseCase;
    private final DeleteGameUseCase deleteGameUseCase;


    //index
    @GetMapping
    public ResponseEntity<?> getAllCategorys(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllGameUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //findBy
    @GetMapping("/{gameId}")
    public ResponseEntity<?> findCategoryById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("gameId") UUID gameId) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByIdUseCase.execute(gameId));
        return ResponseEntity.ok(response);
    }

    //get all active
    @GetMapping("/all/active")
    public ResponseEntity<?> getAllActiveGame(@AuthenticationPrincipal CustomUserDetails userAuthentication) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameActiveUseCase.execute());
        return ResponseEntity.ok(response);
    }

    //get all completed
    @GetMapping("/all/active")
    public ResponseEntity<?> getAllCompletedGame(@AuthenticationPrincipal CustomUserDetails userAuthentication) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameCompletedUseCase.execute());
        return ResponseEntity.ok(response);
    }

    //find By player
    @GetMapping("/find_by_player")
    public ResponseEntity<?> findGameByPlayerId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("playerId") UUID playerId) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByPlayerIdUseCase.execute(playerId));
        return ResponseEntity.ok(response);
    }

    //find By Mode
    @GetMapping("/find_by_mode")
    public ResponseEntity<?> findGameByMode(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                            @RequestBody Mode mode) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByModeUseCase.execute(mode));
        return ResponseEntity.ok(response);
    }

    //find By Status
    @GetMapping("/find_by_status")
    public ResponseEntity<?> findGameByStatus(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody Status status) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByStatusUseCase.execute(status));
        return ResponseEntity.ok(response);
    }
    //find By Mode e Status
    @GetMapping("/find_by_mode_status")
    public ResponseEntity<?> findGameByModeAndStatus(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody  Status status, Mode mode  ) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByModeAndStatusUseCase.execute(mode, status));
        return ResponseEntity.ok(response);
    }

    //find By Btween dates v
    @GetMapping("/find_by_mode_status")
    public ResponseEntity<?> findGameBetween(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                             @RequestBody LocalDateTime start, LocalDateTime end  ) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameBetweenUseCase.execute(start, end));
        return ResponseEntity.ok(response);
    }

    //create
    @PostMapping
    public ResponseEntity<?> register(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                      @RequestBody GameRegistrationRequest game) {
        ResponseDTO<?> response = new ResponseDTO<>(createGameUseCase.execute(game));
        return ResponseEntity.ok(response);
    }

    //update
    @PutMapping("/{gameId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                    @PathVariable("gameId") UUID gameId, @RequestBody UpdateGameDTO request) {
        ResponseDTO<?> response = new ResponseDTO<>(updateGameUseCase.execute(gameId, request));
        return ResponseEntity.ok(response);
    }

    //delete
    @DeleteMapping("/{gameId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                             @PathVariable UUID gameId) {
        ResponseDTO<?> response = new ResponseDTO<>(deleteGameUseCase.execute(gameId));
        return ResponseEntity.ok(response);
    }
}
