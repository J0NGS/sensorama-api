package br.com.starter.application.useCase.badge;

import br.com.starter.application.api.badge.dto.BadgeRegistrationRequest;
import br.com.starter.domain.badge.Badge;
import br.com.starter.domain.badge.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CountAllBadgesUseCase {
    private final BadgeService badgeService;

    public Long execute( ) {
        return  badgeService.countAllBadges();
    }
}
