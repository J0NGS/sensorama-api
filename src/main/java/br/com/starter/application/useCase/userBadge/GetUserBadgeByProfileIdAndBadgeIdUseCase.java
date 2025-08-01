package br.com.starter.application.useCase.userBadge;

import br.com.starter.domain.userBadge.UserBadge;
import br.com.starter.domain.userBadge.UserBadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetUserBadgeByProfileIdAndBadgeIdUseCase {
    private final UserBadgeService userBadgeService;

    public List<UserBadge> execute(UUID profileId, UUID badgeId) {
        return userBadgeService.findByProfileIdAndBadgeId(profileId, badgeId);
    }
}
