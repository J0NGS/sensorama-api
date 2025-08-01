package br.com.starter.application.useCase.badge;

import br.com.starter.domain.badge.Badge;
import br.com.starter.domain.badge.BadgeService;
import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllBadgeUseCase {
    private final BadgeService badgeService;
    public Page<Badge> execute(Pageable pageable) {
        return badgeService.getAllBadges(pageable);
    }
}
