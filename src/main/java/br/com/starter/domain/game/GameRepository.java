package br.com.starter.domain.game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {

    @Query("SELECT g FROM Game g")
    Page<Game> findAllPageable(Pageable pageable);

    @Query("SELECT g FROM Game g")
    List<Game> findAllList();

    @Query("SELECT g FROM Game g WHERE g.status = :status")
    List<Game> findByStatus(Status status);

    @Query("SELECT g FROM Game g WHERE g.mode = :mode")
    List<Game> findByMode(Mode mode);

    @Query("SELECT g FROM Game g WHERE g.status = :status AND g.mode = :mode")
    List<Game> findByStatusAndMode(Status status, Mode mode);

    @Query("SELECT g FROM Game g WHERE g.startTime BETWEEN :startDate AND :endDate")
    List<Game> findByStartTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT g FROM Game g WHERE g.endTime IS NULL")
    List<Game> findActiveGames();

    @Query("SELECT g FROM Game g WHERE g.endTime IS NOT NULL")
    List<Game> findCompletedGames();

    @Query("SELECT g FROM Game g ORDER BY g.startTime DESC")
    List<Game> findAllOrderByStartTimeDesc();

    @Query("SELECT COUNT(g) FROM Game g WHERE g.status = :status")
    Long countByStatus(Status status);

    @Query("SELECT g FROM Game g JOIN g.players p WHERE p.profile.id = :profileId")
    List<Game> findGamesByPlayerId(UUID profileId);

    @Query("SELECT g FROM Game g WHERE g.status = :status AND g.mode = :mode AND SIZE(g.players) = 1")
    List<Game> searchOnlineGameDisponible(Status status, Mode mode);
}
