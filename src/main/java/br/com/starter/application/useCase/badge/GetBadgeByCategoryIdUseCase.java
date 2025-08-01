package br.com.starter.application.useCase.badge;

import br.com.starter.domain.badge.Badge;
import br.com.starter.domain.badge.BadgeService;
import br.com.starter.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetBadgeByCategoryIdUseCase {
    private final BadgeService badgeService;

    public List<Badge> execute(UUID categoryID) {
        return badgeService.findBadgesByCategoryId(categoryID);
    }
}
