package br.com.starter.application.useCase.badge;

import br.com.starter.domain.badge.BadgeService;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
@RequiredArgsConstructor
@Component
public class DeleteBadgeUseCase {
    private final BadgeService badgeService;

    public boolean execute(UUID badgeId) {
        if (badgeService.deleteBadgeById(badgeId)) {
            return true;
        }
        throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Não foi possível deletá-lo.");
    }
}
