package br.com.starter.application.useCase.badge;

import br.com.starter.application.api.badge.dto.BadgeRegistrationRequest;
import br.com.starter.application.api.category.dto.CategoryRegistrationRequest;
import br.com.starter.domain.badge.Badge;


public class CreateBadgeUseCase {
    private final BadgeService badgeService;

    public Badge execute(BadgeRegistrationRequest request) {



        Badge badge = new Badge();
        badge.setName(request.name());
        badge.setDescription(request.description());
        badge.setImageUrl(request.imageUrl());

        return badgeService.createBadge(badge);

    }
}
