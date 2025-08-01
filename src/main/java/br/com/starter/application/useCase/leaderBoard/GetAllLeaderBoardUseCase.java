package br.com.starter.application.useCase.leaderBoard;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import br.com.starter.domain.leaderboard.Leaderboard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import br.com.starter.domain.leaderboard.LeaderboardService;

@Component
@RequiredArgsConstructor

public class GetAllLeaderBoardUseCase {
    private final LeaderboardService leaderboardService;
    public Page<Leaderboard> execute(Pageable pageable) {
        return leaderboardService.getAll(pageable);
    }
}
