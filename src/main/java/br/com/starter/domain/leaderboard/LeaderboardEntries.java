package br.com.starter.domain.leaderboard;

import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.profile.Profile;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class LeaderboardEntries extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "leaderboard_id", referencedColumnName = "id")
    private Leaderboard leaderboard;
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Profile player;

    private Integer score;
    private Integer rank;
    private Integer totalGames;
}
