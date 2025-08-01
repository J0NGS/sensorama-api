package br.com.starter.application.useCase.leaderBoard;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import br.com.starter.domain.leaderboard.Leaderboard;
import br.com.starter.domain.leaderboard.LeaderboardService;
import br.com.starter.domain.leaderboard.LeaderboardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetLeaderBoardByTypeUseCase {
    private final LeaderboardService leaderboardService;
    public List<Leaderboard> execute(LeaderboardType type) {
        return leaderboardService.findByType(type);
    }
}
