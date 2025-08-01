package br.com.starter.application.useCase.leaderBoard;

import br.com.starter.domain.leaderboard.Leaderboard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import br.com.starter.domain.leaderboard.LeaderboardService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor

public class GetAllActiveLeaderBoardsUseCase {
    private final LeaderboardService leaderboardService;
    public List<Leaderboard> execute(LocalDateTime date) {
        return leaderboardService.findActiveLeaderboards(date);
    }
}
