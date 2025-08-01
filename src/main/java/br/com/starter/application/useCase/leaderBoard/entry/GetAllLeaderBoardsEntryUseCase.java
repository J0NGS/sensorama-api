package br.com.starter.application.useCase.leaderBoard.entry;

import br.com.starter.domain.leaderboard.LeaderboardEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import br.com.starter.domain.leaderboard.LeaderboardEntryService;

@Component
@RequiredArgsConstructor
public class GetAllLeaderBoardsEntryUseCase {
    private final LeaderboardEntryService leaderboardEntryService;
    public Page<LeaderboardEntry> execute(Pageable pageable) {
        return leaderboardEntryService.getAll(pageable);
    }
}
