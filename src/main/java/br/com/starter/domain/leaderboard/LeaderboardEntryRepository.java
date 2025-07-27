package br.com.starter.domain.leaderboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntry, UUID> {

    @Query("SELECT l FROM LeaderboardEntry l")
    List<LeaderboardEntry> findAllList();

    @Query("SELECT l FROM LeaderboardEntry l")
    Page<LeaderboardEntry> findAllPageable(Pageable pageable);

    @Query("SELECT l FROM LeaderboardEntry l WHERE l.leaderboard.id = :leaderboardId")
    List<LeaderboardEntry> findByLeaderboardId(UUID leaderboardId);

    @Query("SELECT l FROM LeaderboardEntry l WHERE l.player.id = :playerId")
    List<LeaderboardEntry> findByPlayerId(UUID playerId);

    @Query("SELECT l FROM LeaderboardEntry l WHERE l.leaderboard.id = :leaderboardId ORDER BY l.rank ASC")
    List<LeaderboardEntry> findByLeaderboardIdOrderByRank(UUID leaderboardId);

    @Query("SELECT l FROM LeaderboardEntry l WHERE l.leaderboard.id = :leaderboardId ORDER BY l.score DESC")
    List<LeaderboardEntry> findByLeaderboardIdOrderByScoreDesc(UUID leaderboardId);

    @Query("SELECT l FROM LeaderboardEntry l WHERE l.score >= :minScore")
    List<LeaderboardEntry> findByScoreGreaterThanEqual(Integer minScore);

    @Query("SELECT l FROM LeaderboardEntry l WHERE l.rank <= :maxRank")
    List<LeaderboardEntry> findTopRanked(Integer maxRank);

    @Query("SELECT l FROM LeaderboardEntry l WHERE l.leaderboard.id = :leaderboardId AND l.player.id = :playerId")
    List<LeaderboardEntry> findByLeaderboardIdAndPlayerId(UUID leaderboardId, UUID playerId);

    @Query("SELECT l FROM LeaderboardEntry l WHERE l.totalGames >= :minGames")
    List<LeaderboardEntry> findByTotalGamesGreaterThanEqual(Integer minGames);
}
