package br.com.starter.application.useCase.leaderBoard.entry;

import br.com.starter.domain.leaderboard.Leaderboard;
import br.com.starter.domain.leaderboard.LeaderboardEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class GetLeaderBoardEntryByIdOrderRankUseCase {

    private final LeaderboardEntryService leaderboardEntryService;
    public List<LeaderboardEntry> execute(UUID leaderBoard) {
        return leaderboardEntryService.findActiveLeaderboards(leaderBoard);
    }

}
