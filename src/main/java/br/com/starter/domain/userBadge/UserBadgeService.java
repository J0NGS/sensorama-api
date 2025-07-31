package br.com.starter.domain.userBadge;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserBadgeService {
    private final UserBadgeRepository userBadgeRepository;

    public Page<UserBadge> getAll(Pageable pageable) {
        return userBadgeRepository.findAllPageable(pageable);
    }

    public List<UserBadge> getAllList() {
        return userBadgeRepository.findAllList();
    }

    public UserBadge findById(UUID id) {
        return userBadgeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserBadge não encontrado"));
    }

    public List<UserBadge> findByProfileId(UUID profileId) {
        return userBadgeRepository.findByProfileId(profileId);
    }

    public List<UserBadge> findByBadgeId(UUID badgeId) {
        return userBadgeRepository.findByBadgeId(badgeId);
    }

    public List<UserBadge> findByProfileIdAndBadgeId(UUID profileId, UUID badgeId) {
        return userBadgeRepository.findByProfileIdAndBadgeId(profileId, badgeId);
    }

    public UserBadge save(UserBadge userBadge) {
        if (userBadge.getEarnedAt() == null) {
            userBadge.setEarnedAt(LocalDateTime.now());
        }
        return userBadgeRepository.save(userBadge);
    }

    public UserBadge update(UserBadge userBadge) {
        if (!userBadgeRepository.existsById(userBadge.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserBadge não encontrado");
        }
        return userBadgeRepository.save(userBadge);
    }

    public void deleteById(UUID id) {
        if (!userBadgeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserBadge não encontrado");
        }
        userBadgeRepository.deleteById(id);
    }
}
