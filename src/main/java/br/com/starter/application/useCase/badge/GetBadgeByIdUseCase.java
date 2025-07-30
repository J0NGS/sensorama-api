package br.com.starter.application.useCase.badge;

import br.com.starter.domain.category.Category;

import java.util.UUID;

public class GetBadgeByIdUseCase {
    private final BadgeService badgeService;

    public Badge execute(UUID badgeId) {
        return badgeService.findBadgeById(badgeId);
    }

}
