package br.com.starter.domain.round;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoundRepository extends JpaRepository<Round, UUID> {

    @Query("SELECT r FROM Round r")
    List<Round> findAllList();

    @Query("SELECT r FROM Round r")
    Page<Round> findAllPageable(Pageable pageable);

    @Query("SELECT r FROM Round r WHERE r.game.id = :gameId")
    List<Round> findByGameId(UUID gameId);

    @Query("SELECT r FROM Round r WHERE r.player.id = :playerId")
    List<Round> findByPlayerId(UUID playerId);

    @Query("SELECT r FROM Round r WHERE r.question.id = :questionId")
    List<Round> findByQuestionId(UUID questionId);

    @Query("SELECT r FROM Round r WHERE r.game.id = :gameId AND r.player.id = :playerId")
    List<Round> findByGameIdAndPlayerId(UUID gameId, UUID playerId);
}
