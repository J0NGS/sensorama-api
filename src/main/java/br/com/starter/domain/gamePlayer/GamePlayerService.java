package br.com.starter.domain.gamePlayer;

import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GamePlayerService {
    private final GamePlayerRepository gamePlayerRepository;

    public GamePlayer createGamePlayer(GamePlayer gamePlayer) {
        return gamePlayerRepository.save(gamePlayer);
    }

    public GamePlayer updateGamePlayer(GamePlayer gamePlayer) {
        if (!gamePlayerRepository.existsById(gamePlayer.getId())) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "GamePlayer não encontrado");
        }
        return gamePlayerRepository.save(gamePlayer);
    }

    public GamePlayer findGamePlayerById(UUID id) {
        return gamePlayerRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "GamePlayer não encontrado"));
    }

    public Page<GamePlayer> getAllGames(Pageable pageable) {
        return gamePlayerRepository.findAllPageable(pageable);
    }

    public List<GamePlayer> getAllGamesList() {
        return gamePlayerRepository.findAllList();
    }

    public List<GamePlayer> findByGameId(UUID gameId) {
        return gamePlayerRepository.findByGameId(gameId);
    }

    public List<GamePlayer> findByProfileId(UUID profileId) {
        return gamePlayerRepository.findByProfileId(profileId);
    }

    public GamePlayer findByGameIdAndProfileId(UUID gameId, UUID profileId) {
        GamePlayer gamePlayer = gamePlayerRepository.findByGameIdAndProfileId(gameId, profileId);
        if (gamePlayer == null) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "GamePlayer não encontrado para o jogo e perfil especificados");
        }
        return gamePlayer;
    }

    public List<GamePlayer> findByGameIdOrderByScoreDesc(UUID gameId) {
        return gamePlayerRepository.findByGameIdOrderByScoreDesc(gameId);
    }

    public List<GamePlayer> findCurrentPlayerInGame(UUID gameId) {
        return gamePlayerRepository.findCurrentPlayerInGame(gameId);
    }

    public Double getAverageScoreByGameId(UUID gameId) {
        Double average = gamePlayerRepository.getAverageScoreByGameId(gameId);
        return average != null ? average : 0.0;
    }

    public void deleteGamePlayer(UUID id) {
        if (!gamePlayerRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "GamePlayer não encontrado");
        }
        gamePlayerRepository.deleteById(id);
    }

    public void switchTurn(UUID gameId) {
        List<GamePlayer> players = findByGameId(gameId);

        if (players.isEmpty()) {
            throw new FrontDisplayableException(HttpStatus.BAD_REQUEST, "Nenhum jogador encontrado para o jogo");
        }

        GamePlayer currentPlayer = players.stream()
                .filter(GamePlayer::isCurrentTurn)
                .findFirst()
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.BAD_REQUEST, "Nenhum jogador com turno ativo encontrado"));

        currentPlayer.setCurrentTurn(false);
        gamePlayerRepository.save(currentPlayer);

        int nextTurnOrder = currentPlayer.getTurnOrder() + 1;

        int maxTurnOrder = players.stream()
                .mapToInt(GamePlayer::getTurnOrder)
                .max()
                .orElse(1);

        if (nextTurnOrder > maxTurnOrder) {
            nextTurnOrder = 1;
        }

        final int finalNextTurnOrder = nextTurnOrder;
        GamePlayer nextPlayer = players.stream()
                .filter(p -> p.getTurnOrder() == finalNextTurnOrder)
                .findFirst()
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.BAD_REQUEST, "Próximo jogador não encontrado"));

        nextPlayer.setCurrentTurn(true);
        gamePlayerRepository.save(nextPlayer);
    }

    public void updateScore(UUID gamePlayerId, Integer newScore) {
        GamePlayer gamePlayer = findGamePlayerById(gamePlayerId);
        gamePlayer.setScore(newScore);
        gamePlayerRepository.save(gamePlayer);
    }
}
