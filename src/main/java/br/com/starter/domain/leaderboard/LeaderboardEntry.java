package br.com.starter.domain.leaderboard;

import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.web.service.annotation.GetExchange;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "leaderboard_entries")
@Audited
public class LeaderboardEntry extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "leaderboard_id", referencedColumnName = "id")
    private Leaderboard leaderboard;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Profile player;

    private Integer score;
    private Integer rank;
    private Integer totalGames;

    @Version
    private Long version;
}
