package br.com.starter.application.useCase.badge;

import br.com.starter.application.api.badge.dto.UpdateBadgeDTO;
import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.domain.badge.Badge;
import br.com.starter.domain.badge.BadgeService;
import br.com.starter.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UpdateBadgeUseCase {
    private final BadgeService badgeService;

    public Badge execute(UUID badgeId, UpdateBadgeDTO updateBadgeDTO) {
        return badgeService.updateBadge(badgeId, updateBadgeDTO);
    }

}
