package br.com.starter.application.useCase.badge;

import br.com.starter.application.api.badge.dto.UpdateBadgeDTO;
import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.domain.category.Category;

import java.util.UUID;

public class UpdateBadgeUseCase {
    private final BadgeService badgeService;

    public Badge execute(UUID badgeId, UpdateBadgeDTO updateBadgeDTO) {
        return badgeService.updateBadge(badgeId, updateBadgeDTO);
    }

}
