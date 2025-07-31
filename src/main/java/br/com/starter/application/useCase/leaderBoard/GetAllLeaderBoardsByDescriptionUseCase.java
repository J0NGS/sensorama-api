package br.com.starter.application.useCase.leaderBoard;

import br.com.starter.domain.leaderboard.Leaderboard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor

public class GetAllLeaderBoardsByDescriptionUseCase {
    private final LeaderboardService leaderboardService;
    public List<Leaderboard> execute(String description) {
        return leaderboardService.findByDescriptionContainingIgnoreCase(description);
    }

}
