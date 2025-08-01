package br.com.starter.application.api.game;


import br.com.starter.application.api.common.ResponseDTO;
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
    private final GetGameByStatusAndModeUseCase getGameByStatusAndModeUseCase;
    private final GetGameBetweenUseCase getGameBetweenUseCase;

    private final CreateGameUseCase createGameUseCase;
    private final CreateGameOnlineUseCase createGameOnlineUseCase;
    private final UpdateGameUseCase updateGameUseCase;
    private final DeleteGameUseCase deleteGameUseCase;

    private final CountAllGamesUseCase countAllGamesUseCase;
    private final ExistGameByIdUseCase existGameByIdUseCase;
    private final CountGamesByStatusUseCase countGamesByStatusUseCase;
    private final GetAllOrderbyStartTimeDescUseCase getAllOrderbyStartTimeDescUseCase;
    private final GetAllGamesListUseCase getAllGamesListUseCase;
    private final SwitchTurnUseCase switchTurnUseCase;
    private final EndGameUseGame endGameUseGame;

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
    @GetMapping("/Active")
    public ResponseEntity<?> getAllActiveGame(@AuthenticationPrincipal CustomUserDetails userAuthentication) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameActiveUseCase.execute());
        return ResponseEntity.ok(response);
    }

    //get all completed
    @GetMapping("/Completed")
    public ResponseEntity<?> getAllCompletedGame(@AuthenticationPrincipal CustomUserDetails userAuthentication) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameCompletedUseCase.execute());
        return ResponseEntity.ok(response);
    }

    //find By player
    @GetMapping("/Player/{playerId}")
    public ResponseEntity<?> findGameByPlayerId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("playerId") UUID playerId) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByPlayerIdUseCase.execute(playerId));
        return ResponseEntity.ok(response);
    }

    //find By Mode
    @GetMapping("/Mode")
    public ResponseEntity<?> findGameByMode(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                            @RequestBody Mode mode) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByModeUseCase.execute(mode));
        return ResponseEntity.ok(response);
    }

    //find By Status
    @GetMapping("/Status")
    public ResponseEntity<?> findGameByStatus(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody Status status) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByStatusUseCase.execute(status));
        return ResponseEntity.ok(response);
    }
    //find By Mode e Status
    @GetMapping("/Status/Mode")
    public ResponseEntity<?> findGameByModeAndStatus(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody  Status status, Mode mode  ) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameByStatusAndModeUseCase.execute(status, mode));
        return ResponseEntity.ok(response);
    }

    //find By Btween dates v
    @GetMapping("/Range_Date")
    public ResponseEntity<?> findGameBetween(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                             @RequestBody LocalDateTime start, LocalDateTime end  ) {
        ResponseDTO<?> response = new ResponseDTO<>(getGameBetweenUseCase.execute(start, end));
        return ResponseEntity.ok(response);
    }

    //create ofline game
    @PostMapping("/{profileId}")
    public ResponseEntity<?> createoOflineGame(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                      @PathVariable("profileId") UUID profileId) {
        ResponseDTO<?> response = new ResponseDTO<>(createGameUseCase.execute(profileId));
        return ResponseEntity.ok(response);
    }

    //create online game
    @PostMapping("online/{profileId}")
    public ResponseEntity<?> createOnlineGame(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                      @PathVariable("profileId") UUID profileId) {
        ResponseDTO<?> response = new ResponseDTO<>(createGameOnlineUseCase.execute(profileId));
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
                                    @PathVariable("gameId") UUID gameId) {
        ResponseDTO<?> response = new ResponseDTO<>(deleteGameUseCase.execute(gameId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Count")
    public ResponseEntity<?> countAllGames(@AuthenticationPrincipal CustomUserDetails userAuthentication
                                             ) {
        ResponseDTO<?> response = new ResponseDTO<>(countAllGamesUseCase.execute());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Exist/{gameId}")
    public ResponseEntity<?> exist(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                   @PathVariable("gameId") UUID gameId
    ) {
        ResponseDTO<?> response = new ResponseDTO<>(existGameByIdUseCase.execute(gameId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Status/Count")
    public ResponseEntity<?> countGamesByStatus(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody Status status) {
        ResponseDTO<?> response = new ResponseDTO<>(countGamesByStatusUseCase.execute(status));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/List/Desc")
    public ResponseEntity<?> listDesc(@AuthenticationPrincipal CustomUserDetails userAuthentication
                                               ) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllOrderbyStartTimeDescUseCase.execute());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/List")
    public ResponseEntity<?> list(@AuthenticationPrincipal CustomUserDetails userAuthentication
    ) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllGamesListUseCase.execute());
        return ResponseEntity.ok(response);
    }

    //create ofline game
    @PutMapping("/switchTurn/{gameId}")
    public ResponseEntity<?> switchTurn(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                               @PathVariable("gameId") UUID gameId) {
        ResponseDTO<?> response = new ResponseDTO<>(switchTurnUseCase.execute(gameId));
        return ResponseEntity.ok(response);
    }
    @PutMapping("/endGame/{gameId}")
    public ResponseEntity<?> endGame(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                        @PathVariable("gameId") UUID gameId) {
        ResponseDTO<?> response = new ResponseDTO<>(endGameUseGame.execute(gameId));
        return ResponseEntity.ok(response);
    }
}
