package br.com.starter.domain.leaderboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, UUID> {
    @Query("SELECT l FROM Leaderboard l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Leaderboard> findByNameContainingIgnoreCase(String name);

    @Query("SELECT l FROM Leaderboard l")
    Page<Leaderboard> findAllPageable(Pageable pageable);

    @Query("SELECT l FROM Leaderboard l")
    List<Leaderboard> findAllList();

    @Query("SELECT l FROM Leaderboard l WHERE l.type = :type")
    List<Leaderboard> findByType(LeaderboardType type);

    @Query("SELECT l FROM Leaderboard l WHERE l.startDate <= :currentDate AND (l.endDate IS NULL OR l.endDate >= :currentDate)")
    List<Leaderboard> findActiveLeaderboards(LocalDateTime currentDate);

    @Query("SELECT l FROM Leaderboard l WHERE l.endDate < :currentDate")
    List<Leaderboard> findExpiredLeaderboards(LocalDateTime currentDate);

    @Query("SELECT l FROM Leaderboard l WHERE l.startDate > :currentDate")
    List<Leaderboard> findFutureLeaderboards(LocalDateTime currentDate);

    @Query("SELECT l FROM Leaderboard l WHERE l.startDate BETWEEN :startDate AND :endDate")
    List<Leaderboard> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT l FROM Leaderboard l WHERE LOWER(l.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Leaderboard> findByDescriptionContainingIgnoreCase(String description);
}
