package br.com.starter.application.useCase.leaderBoard.entry;

import br.com.starter.domain.leaderboard.Leaderboard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllLeaderBoardsEntryUseCase {
    private final LeaderboardEntryService leaderboardEntryService;
    public Page<Leaderboard> execute(Pageable pageable) {
        return leaderboardEntryService.getAll(pageable);
    }
}
