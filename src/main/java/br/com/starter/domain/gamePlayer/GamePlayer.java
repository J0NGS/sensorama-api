package br.com.starter.domain.gamePlayer;

import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.game.Game;
import br.com.starter.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Audited
@Table(name = "game_players")
@Getter
@Setter
public class GamePlayer extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Profile profile;
    private Integer score;
    private Integer turnOrder;
    private boolean currentTurn;
    @Version
    private Long version;
}
