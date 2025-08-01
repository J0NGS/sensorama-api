package br.com.starter.application.api.leaderBoard;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.leaderBoard.*;
import br.com.starter.domain.leaderboard.LeaderboardType;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/leaderBoard")
@RequiredArgsConstructor
public class LeaderBoardController {
    private final GetAllLeaderBoardUseCase getAllLeaderBoardUseCase;
    private final GetAllLeaderBoardsByDateRangeUseCase getAllLeaderBoardsByDateRangeUseCase;
    private final GetAllActiveLeaderBoardsUseCase getAllActiveLeaderBoardsUseCase;
    private final GetAllExpiredLeaderBoardsUseCase getAllExpiredLeaderBoardsUseCase;
    private final GetAllFutureLeaderBoardsUseCase getAllFutureLeaderBoardsUseCase;
    private final GetAllLeaderBoardsByDescriptionUseCase getAllLeaderBoardsByDescriptionUseCase;
    private final GetLeaderBoardByNameUseCase getLeaderBoardByNameUseCase;
    private final GetLeaderBoardByTypeUseCase getLeaderBoardByTypeUseCase;

    //index
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllLeaderBoardUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //daterange
    @PostMapping("/dateRange")
    public ResponseEntity<?> findByDateRange(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody LocalDateTime start, LocalDateTime end) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllLeaderBoardsByDateRangeUseCase.execute(start, end));
        return ResponseEntity.ok(response);
    }

    //active
    @PostMapping("/active")
    public ResponseEntity<?> findActive(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody LocalDateTime date) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllActiveLeaderBoardsUseCase.execute(date));
        return ResponseEntity.ok(response);
    }

    //expired
    @PostMapping("/expired")
    public ResponseEntity<?> findExpired(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody LocalDateTime date) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllExpiredLeaderBoardsUseCase.execute(date));
        return ResponseEntity.ok(response);
    }

    //future
    @PostMapping("/future")
    public ResponseEntity<?> findFuture(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                         @RequestBody LocalDateTime date) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllFutureLeaderBoardsUseCase.execute(date));
        return ResponseEntity.ok(response);
    }

    //find By description
    @PostMapping("/description")
    public ResponseEntity<?> findByDescription(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                        @RequestBody String description) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllLeaderBoardsByDescriptionUseCase.execute(description));
        return ResponseEntity.ok(response);
    }

    //find By name
    @PostMapping("/name")
    public ResponseEntity<?> findByName(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                               @RequestBody String name) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardByNameUseCase.execute(name));
        return ResponseEntity.ok(response);
    }

    //find By name
    @PostMapping("/type")
    public ResponseEntity<?> findByType(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                        @RequestBody LeaderboardType type) {
        ResponseDTO<?> response = new ResponseDTO<>(getLeaderBoardByTypeUseCase.execute(type));
        return ResponseEntity.ok(response);
    }
}
