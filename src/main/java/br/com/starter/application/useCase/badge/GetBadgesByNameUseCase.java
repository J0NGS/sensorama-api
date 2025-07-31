package br.com.starter.application.useCase.badge;

import br.com.starter.domain.badge.Badge;
import br.com.starter.domain.badge.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetBadgesByNameUseCase {
    private final BadgeService badgeService;

    public List<Badge> execute(String name) {
        return badgeService.findBadgesByNameContainingIgnoreCase(name);
    }
}
