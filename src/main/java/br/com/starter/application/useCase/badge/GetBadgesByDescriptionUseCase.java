package br.com.starter.application.useCase.badge;

import br.com.starter.domain.badge.Badge;
import br.com.starter.domain.badge.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetBadgesByDescriptionUseCase {
    private final BadgeService badgeService;

    public List<Badge> execute(String description ) {
        return badgeService.findBadgesByDescriptionContainingIgnoreCase(description);
    }
}
