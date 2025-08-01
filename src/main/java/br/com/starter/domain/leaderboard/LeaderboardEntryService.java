package br.com.starter.domain.leaderboard;

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
public class LeaderboardEntryService {
    private final LeaderboardEntryRepository leaderboardEntryRepository;

    public LeaderboardEntry createLeaderboardEntry(LeaderboardEntry leaderboardEntry) {
        return leaderboardEntryRepository.save(leaderboardEntry);
    }

    public LeaderboardEntry updateLeaderboardEntry(LeaderboardEntry leaderboardEntry) {
        if (!leaderboardEntryRepository.existsById(leaderboardEntry.getId())) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "LeaderboardEntry não encontrado");
        }
        return leaderboardEntryRepository.save(leaderboardEntry);
    }

    public LeaderboardEntry findLeaderboardEntryById(UUID id) {
        return leaderboardEntryRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "LeaderboardEntry não encontrado"));
    }

    public Page<LeaderboardEntry> getAll(Pageable pageable) {
        return leaderboardEntryRepository.findAllPageable(pageable);
    }

    public List<LeaderboardEntry> getAllList() {
        return leaderboardEntryRepository.findAllList();
    }

    public List<LeaderboardEntry> findByLeaderboardId(UUID leaderboardId) {
        return leaderboardEntryRepository.findByLeaderboardId(leaderboardId);
    }

    public List<LeaderboardEntry> findByPlayerId(UUID playerId) {
        return leaderboardEntryRepository.findByPlayerId(playerId);
    }

    public List<LeaderboardEntry> findActiveLeaderboards(UUID leaderboardId) {
        return leaderboardEntryRepository.findByLeaderboardIdOrderByRank(leaderboardId);
    }

    public List<LeaderboardEntry> findByLeaderboardIdOrderByRank(UUID leaderboardId) {
        return leaderboardEntryRepository.findByLeaderboardIdOrderByRank(leaderboardId);
    }

    public List<LeaderboardEntry> findByLeaderboardIdOrderByScoreDesc(UUID leaderboardId) {
        return leaderboardEntryRepository.findByLeaderboardIdOrderByScoreDesc(leaderboardId);
    }

    public List<LeaderboardEntry> findByScoreGreaterThanEqual(Integer minScore) {
        return leaderboardEntryRepository.findByScoreGreaterThanEqual(minScore);
    }

    public List<LeaderboardEntry> findTopRanked(Integer maxRank) {
        return leaderboardEntryRepository.findTopRanked(maxRank);
    }

    public List<LeaderboardEntry> findByLeaderboardIdAndPlayerId(UUID leaderboardId, UUID playerId) {
        return leaderboardEntryRepository.findByLeaderboardIdAndPlayerId(leaderboardId, playerId);
    }

    public List<LeaderboardEntry> findByTotalGamesGreaterThanEqual(Integer minGames) {
        return leaderboardEntryRepository.findByTotalGamesGreaterThanEqual(minGames);
    }

    public void deleteLeaderboardEntry(UUID id) {
        if (!leaderboardEntryRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "LeaderboardEntry não encontrado");
        }
        leaderboardEntryRepository.deleteById(id);
    }

    public void updateScore(UUID entryId, Integer newScore) {
        LeaderboardEntry entry = findLeaderboardEntryById(entryId);
        entry.setScore(newScore);
        leaderboardEntryRepository.save(entry);
    }

    public void updateRank(UUID entryId, Integer newRank) {
        LeaderboardEntry entry = findLeaderboardEntryById(entryId);
        entry.setRank(newRank);
        leaderboardEntryRepository.save(entry);
    }

    public void updateTotalGames(UUID entryId, Integer totalGames) {
        LeaderboardEntry entry = findLeaderboardEntryById(entryId);
        entry.setTotalGames(totalGames);
        leaderboardEntryRepository.save(entry);
    }

    public void incrementTotalGames(UUID entryId) {
        LeaderboardEntry entry = findLeaderboardEntryById(entryId);
        entry.setTotalGames(entry.getTotalGames() != null ? entry.getTotalGames() + 1 : 1);
        leaderboardEntryRepository.save(entry);
    }

    public LeaderboardEntry findOrCreateEntry(UUID leaderboardId, UUID playerId) {
        List<LeaderboardEntry> entries = findByLeaderboardIdAndPlayerId(leaderboardId, playerId);

        if (!entries.isEmpty()) {
            return entries.get(0); // Retorna a primeira entrada encontrada
        }

        // Se não encontrou, cria uma nova entrada
        LeaderboardEntry newEntry = new LeaderboardEntry();
        // Note: Você precisará buscar os objetos Leaderboard e Profile pelos IDs
        // newEntry.setLeaderboard(leaderboardService.findLeaderboardById(leaderboardId));
        // newEntry.setPlayer(profileService.findProfileById(playerId));
        newEntry.setScore(0);
        newEntry.setRank(0);
        newEntry.setTotalGames(0);

        return createLeaderboardEntry(newEntry);
    }
}
