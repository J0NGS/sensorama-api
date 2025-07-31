package br.com.starter.domain.round;

import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;

    public Page<Round> getAll(Pageable pageable) {
        return roundRepository.findAllPageable(pageable);
    }

    public List<Round> getAllList() {
        return roundRepository.findAllList();
    }

    public Round findById(UUID id) {
        return roundRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round não encontrado"));
    }

    public List<Round> findByGameId(UUID gameId) {
        return roundRepository.findByGameId(gameId);
    }

    public List<Round> findByPlayerId(UUID playerId) {
        return roundRepository.findByPlayerId(playerId);
    }

    public List<Round> findByQuestionId(UUID questionId) {
        return roundRepository.findByQuestionId(questionId);
    }

    public List<Round> findByGameIdAndPlayerId(UUID gameId, UUID playerId) {
        return roundRepository.findByGameIdAndPlayerId(gameId, playerId);
    }

    public Round save(Round round) {
        if (round.getStartTime() == null) {
            round.setStartTime(LocalTime.now());
        }
        return roundRepository.save(round);
    }

    public Round update(Round round) {
        if (!roundRepository.existsById(round.getId())) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Round não encontrado");
        }
        return roundRepository.save(round);
    }

    public Round startRound(UUID roundId) {
        Round round = findById(roundId);
        round.setStartTime(LocalTime.now());
        return roundRepository.save(round);
    }

    public Round endRound(UUID roundId) {
        Round round = findById(roundId);
        round.setEndTime(LocalTime.now());
        return roundRepository.save(round);
    }

    public void deleteById(UUID id) {
        if (!roundRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Round não encontrado");
        }
        roundRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return roundRepository.existsById(id);
    }
}
