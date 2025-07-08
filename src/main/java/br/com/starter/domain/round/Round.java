package br.com.starter.domain.round;

import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.game.Game;
import br.com.starter.domain.gamePlayer.GamePlayer;
import br.com.starter.domain.question.Question;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Audited
@Setter
@Getter
@Entity
@Table
public class Round extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private GamePlayer player;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;
    @JsonFormat(pattern = "'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalTime startTime;
    @JsonFormat(pattern = "'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalTime endTime;
    @Version
    private Long version;
}
