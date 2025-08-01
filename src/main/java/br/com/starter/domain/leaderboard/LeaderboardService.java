package br.com.starter.domain.leaderboard;

import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    public Leaderboard createLeaderboard(Leaderboard leaderboard) {
        leaderboard.setLastUpdated(LocalDateTime.now());
        return leaderboardRepository.save(leaderboard);
    }

    public Leaderboard updateLeaderboard(Leaderboard leaderboard) {
        if (!leaderboardRepository.existsById(leaderboard.getId())) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Leaderboard não encontrado");
        }
        leaderboard.setLastUpdated(LocalDateTime.now());
        return leaderboardRepository.save(leaderboard);
    }

    public Leaderboard findLeaderboardById(UUID id) {
        return leaderboardRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Leaderboard não encontrado"));
    }

    public Page<Leaderboard> getAll(Pageable pageable) {
        return leaderboardRepository.findAllPageable(pageable);
    }

    public List<Leaderboard> getAllList() {
        return leaderboardRepository.findAllList();
    }

    public List<Leaderboard> findByNameContainingIgnoreCase(String name) {
        return leaderboardRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Leaderboard> findByType(LeaderboardType type) {
        return leaderboardRepository.findByType(type);
    }

    public List<Leaderboard> findActiveLeaderboards(LocalDateTime date) {
        return leaderboardRepository.findActiveLeaderboards(date);
    }

    public List<Leaderboard> findExpiredLeaderboards(LocalDateTime date) {
        return leaderboardRepository.findExpiredLeaderboards(date);
    }

    public List<Leaderboard> findFutureLeaderboards(LocalDateTime date) {
        return leaderboardRepository.findFutureLeaderboards(date);
    }

    public List<Leaderboard> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return leaderboardRepository.findByDateRange(startDate, endDate);
    }

    public List<Leaderboard> findByDescriptionContainingIgnoreCase(String description) {
        return leaderboardRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public void deleteLeaderboard(UUID id) {
        if (!leaderboardRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Leaderboard não encontrado");
        }
        leaderboardRepository.deleteById(id);
    }

    public void updateLastUpdated(UUID leaderboardId) {
        Leaderboard leaderboard = findLeaderboardById(leaderboardId);
        leaderboard.setLastUpdated(LocalDateTime.now());
        leaderboardRepository.save(leaderboard);
    }

    public boolean isLeaderboardActive(UUID id) {
        Leaderboard leaderboard = findLeaderboardById(id);
        LocalDateTime now = LocalDateTime.now();
        return leaderboard.getStartDate().isBefore(now) || leaderboard.getStartDate().isEqual(now) &&
               (leaderboard.getEndDate() == null || leaderboard.getEndDate().isAfter(now) || leaderboard.getEndDate().isEqual(now));
    }

    public boolean isLeaderboardExpired(UUID id) {
        Leaderboard leaderboard = findLeaderboardById(id);
        LocalDateTime now = LocalDateTime.now();
        return leaderboard.getEndDate() != null && leaderboard.getEndDate().isBefore(now);
    }

    public boolean isLeaderboardFuture(UUID id) {
        Leaderboard leaderboard = findLeaderboardById(id);
        LocalDateTime now = LocalDateTime.now();
        return leaderboard.getStartDate().isAfter(now);
    }
}
