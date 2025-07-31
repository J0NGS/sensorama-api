package br.com.starter.application.useCase.leaderBoard;

import br.com.starter.domain.leaderboard.Leaderboard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor

public class GetAllLeaderBoardsByDateRangeUseCase {
    private final LeaderboardService leaderboardService;
    public List<Leaderboard> execute(LocalDateTime start, LocalDateTime end) {
        return leaderboardService.findByDateRange(start,end);
    }

}
