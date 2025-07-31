package br.com.starter.application.api.game.dto;

import br.com.starter.domain.game.Mode;
import br.com.starter.domain.game.Status;
import br.com.starter.domain.gamePlayer.GamePlayer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UpdateGameDTO {
     public Mode mode;
     public Status status;
     public List<GamePlayer> playerIds; // IDs dos jogadores
     public LocalDateTime startTime;
     public LocalDateTime endTime;
}
