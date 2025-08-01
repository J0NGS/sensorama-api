package br.com.starter.application.api.leaderBoard;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.leaderBoard.*;
import br.com.starter.application.useCase.leaderBoard.entry.*;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/leaderBoardEntry")
@RequiredArgsConstructor

public class LeaderBoardEntryController {
    private final GetAllLeaderBoardsEntryUseCase getAllLeaderBoardsEntryUseCase;
    private final GetLeaderBoardEntryByTotalGamesUseCase getLeaderBoardEntryByTotalGamesUseCase;
    private final GetLeaderBoardEntryByScoreUseCase getLeaderBoardEntryByScoreUseCase;
    private final GetLeaderBoardEntryTopRankedUseCase getLeaderBoardEntryTopRankedUseCase;
    private final GetLeaderBoardsEntryByIdAndPlayerIdUseCase getLeaderBoardsEntryByIdAndPlayerIdUseCase;
    private final GetLeaderBoardEntryByIdOrderRankUseCase getLeaderBoardEntryByIdOrderRankUseCase;
    private final GetLeaderBoardEntryByIdOrderScoreUseCase getLeaderBoardEntryByIdOrderScoreUseCase;
    private final GetLeaderBoardsEntryByIdUseCase getLeaderBoardsEntryByIdUseCase;
    private final GetLeaderBoardEntryByPlayerIdUseCase getLeaderBoardEntryByPlayerIdUseCase;

    //index
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllLeaderBoardsEntryUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //total
    @PostMapping("/total")
    public ResponseEntity<?> findByTotalGames(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                             @RequestBody Integer minGames) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardEntryByTotalGamesUseCase.execute(minGames));
        return ResponseEntity.ok(response);
    }

    //score
    @PostMapping("/total")
    public ResponseEntity<?> findByScore(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody Integer minScore) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardEntryByScoreUseCase.execute(minScore));
        return ResponseEntity.ok(response);
    }

    //rank
    @PostMapping("/rank")
    public ResponseEntity<?> findByRank(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                         @RequestBody Integer maxRank) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardEntryTopRankedUseCase.execute(maxRank));
        return ResponseEntity.ok(response);
    }

    //findby leaderBoard
    @GetMapping("/leaderBoard/{leaderBoardId}")
    public ResponseEntity<?> findByLeaderBoard(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                                         @PathVariable("leaderBoardId") UUID leaderBoardId
    ) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardsEntryByIdUseCase.execute(leaderBoardId));
        return ResponseEntity.ok(response);
    }

    //findby player
    @GetMapping("/player/{playerId}")
    public ResponseEntity<?> findByPlayer(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                               @PathVariable("playerId") UUID playerId
    ) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardEntryByPlayerIdUseCase.execute(playerId));
        return ResponseEntity.ok(response);
    }

    //findby leaderBoard player
    @GetMapping("/leaderBoard/{leaderBoardId}/player/{playerId}")
    public ResponseEntity<?> findByLeaderBoardPlayer(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                        @PathVariable("leaderBoardId") UUID leaderBoardId,
                                        @PathVariable("playerId") UUID playerId) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardsEntryByIdAndPlayerIdUseCase.execute(leaderBoardId, playerId));
        return ResponseEntity.ok(response);
    }

    //findby leaderBoard orderRank
    @GetMapping("/leaderBoard/orderRank/{leaderBoardId}")
    public ResponseEntity<?> findByLeaderBoardOrderRank(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                                     @PathVariable("leaderBoardId") UUID leaderBoardId
                                                     ) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardEntryByIdOrderRankUseCase.execute(leaderBoardId));
        return ResponseEntity.ok(response);
    }

    //findby leaderBoard orderScore
    @GetMapping("/leaderBoard/orderScore/{leaderBoardId}")
    public ResponseEntity<?> findByLeaderBoardOrderScore(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                                        @PathVariable("leaderBoardId") UUID leaderBoardId
    ) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardEntryByIdOrderScoreUseCase.execute(leaderBoardId));
        return ResponseEntity.ok(response);
    }

}
