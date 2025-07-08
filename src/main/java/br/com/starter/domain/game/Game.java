package br.com.starter.domain.game;

import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.gamePlayer.GamePlayer;
import br.com.starter.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Audited
@Table(name = "games")
public class Game extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    private Mode mode;
    private Status status;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GamePlayer> players;
    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Profile winner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Version
    private Long version;
}
