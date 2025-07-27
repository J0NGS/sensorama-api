package br.com.starter.domain.gamePlayer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, UUID> {
    @Query("SELECT g FROM GamePlayer g")
    Page<GamePlayer> findAllPageable(Pageable pageable);

    @Query("SELECT g FROM GamePlayer g")
    List<GamePlayer> findAllList();

    @Query("SELECT gp FROM GamePlayer gp WHERE gp.game.id = :gameId")
    List<GamePlayer> findByGameId(UUID gameId);

    @Query("SELECT gp FROM GamePlayer gp WHERE gp.profile.id = :profileId")
    List<GamePlayer> findByProfileId(UUID profileId);

    @Query("SELECT gp FROM GamePlayer gp WHERE gp.game.id = :gameId AND gp.profile.id = :profileId")
    GamePlayer findByGameIdAndProfileId(UUID gameId, UUID profileId);

    @Query("SELECT gp FROM GamePlayer gp WHERE gp.game.id = :gameId ORDER BY gp.score DESC")
    List<GamePlayer> findByGameIdOrderByScoreDesc(UUID gameId);

    @Query("SELECT gp FROM GamePlayer gp WHERE gp.game.id = :gameId AND gp.currentTurn = true")
    List<GamePlayer> findCurrentPlayerInGame(UUID gameId);

    @Query("SELECT AVG(gp.score) FROM GamePlayer gp WHERE gp.game.id = :gameId")
    Double getAverageScoreByGameId(UUID gameId);
}
