package br.com.starter.application.useCase.leaderBoard;

import br.com.starter.domain.leaderboard.Leaderboard;
import br.com.starter.domain.leaderboard.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor

public class GetAllExpiredLeaderBoardsUseCase {
    private final LeaderboardService leaderboardService;
    public List<Leaderboard> execute(LocalDateTime date) {
        return leaderboardService.findExpiredLeaderboards(date);
    }
}
