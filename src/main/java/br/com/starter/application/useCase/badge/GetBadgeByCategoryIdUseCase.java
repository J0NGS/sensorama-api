package br.com.starter.application.useCase.badge;

import br.com.starter.domain.category.Category;

import java.util.List;
import java.util.UUID;

public class GetBadgeByCategoryIdUseCase {
    private final BadgeService badgeService;

    public List<Badge> execute(UUID categoryID) {
        return badgeService.findBadgesByCategoryId(categoryID);
    }
}
