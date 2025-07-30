package br.com.starter.application.api.game.dto;

import br.com.starter.domain.game.Mode;
import br.com.starter.domain.game.Status;
import br.com.starter.domain.gamePlayer.GamePlayer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GameRegistrationRequest(
        Mode mode,
        Status status,
        List<GamePlayer> players,
        LocalDateTime startTime,
        LocalDateTime endTime)
   {

    public GameRegistrationRequest(Mode mode, Status status, List<GamePlayer> players, LocalDateTime startTime, LocalDateTime endTime) {
        this.mode = mode;
        this.status = status;
        this.players = players;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
