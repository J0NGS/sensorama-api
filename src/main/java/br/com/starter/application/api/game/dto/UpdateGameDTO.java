package br.com.starter.application.api.game.dto;

import br.com.starter.domain.game.Mode;
import br.com.starter.domain.game.Status;
import br.com.starter.domain.gamePlayer.GamePlayer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UpdateGameDTO {
     Mode mode;
     Status status;
     List<GamePlayer> playerIds; // IDs dos jogadores
     LocalDateTime startTime;
     LocalDateTime endTime;
}
