package br.com.starter.application.useCase.userBadge;

import br.com.starter.domain.round.Round;
import br.com.starter.domain.userBadge.UserBadge;

import java.util.List;
import java.util.UUID;

public class GetUserBadgebyBadgeIdUseCase {
    private final UserBadgeService userBadgeService;

    public List<UserBadge> execute(UUID badgeId) {
        return userBadgeService.findByBadgeId(badgeId);
    }

}
