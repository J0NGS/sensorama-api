package br.com.starter.application.useCase.leaderBoard;

import br.com.starter.domain.leaderboard.Leaderboard;
import br.com.starter.domain.leaderboard.LeaderboardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import br.com.starter.domain.leaderboard.LeaderboardService;

import java.util.List;
@Component
@RequiredArgsConstructor

public class GetLeaderBoardByNameUseCase {
    private final LeaderboardService leaderboardService;
    public List<Leaderboard> execute(String name) {
        return leaderboardService.findByNameContainingIgnoreCase(name);
    }
}
