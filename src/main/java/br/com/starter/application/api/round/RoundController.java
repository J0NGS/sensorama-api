package br.com.starter.application.api.round;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.round.*;
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
@RequestMapping("/sensorama/api/round")
@RequiredArgsConstructor
public class RoundController {
    private final GetRoundByIdUseCase getRoundByIdUseCase;
    private final GetRoundByGameIdUseCase getRoundByGameIdUseCase;
    private final GetRoundByGameIdAndPlayerIdUseCase getRoundByGameIdAndPlayerIdUseCase;

    //findBy
    @GetMapping("/{roundId}")
    public ResponseEntity<?> findById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("roundId") UUID roundId) {
        ResponseDTO<?> response = new ResponseDTO<>(getRoundByIdUseCase.execute(roundId));
        return ResponseEntity.ok(response);
    }

    //findBy gameId
    @GetMapping("/{gameId}")
    public ResponseEntity<?> findByGameId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("gameId") UUID gameId) {
        ResponseDTO<?> response = new ResponseDTO<>(getRoundByGameIdUseCase.execute(gameId));
        return ResponseEntity.ok(response);
    }

    //findBy gameId e playerId
    @GetMapping("/{gameId}/{playerId}")
    public ResponseEntity<?> findByGameIdPlayerId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                            @PathVariable("gameId") UUID gameId,
                                            @PathVariable("playerId") UUID playerId) {
        ResponseDTO<?> response = new ResponseDTO<>(getRoundByGameIdAndPlayerIdUseCase.execute(gameId,playerId ));
        return ResponseEntity.ok(response);
    }
}
