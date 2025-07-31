package br.com.starter.application.useCase.userBadge;

import br.com.starter.domain.userBadge.UserBadge;

import java.util.List;
import java.util.UUID;

public class GetUserBadgeByProfileIdUseCase {
    private final UserBadgeService userBadgeService;

    public List<UserBadge> execute(UUID profileId) {
        return userBadgeService.findByProfileId(profileId);
    }

}
