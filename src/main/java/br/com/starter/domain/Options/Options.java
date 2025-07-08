package br.com.starter.domain.Options;

import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.game.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Audited
@Getter
@Setter
@Table(name = "options")
public class Options extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;
    private String text;
    private String imageUrl;
    private boolean isCorrect;
    @Version
    private Long version;
}
