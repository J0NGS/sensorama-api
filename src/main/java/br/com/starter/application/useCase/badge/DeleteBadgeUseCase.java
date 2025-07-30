package br.com.starter.application.useCase.badge;

import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DeleteBadgeUseCase {
    private final BadgeService badgeService;

    public boolean execute(UUID badgeId) {
        if (badgeService.deleteBadgeById(badgeId)) {
            return true;
        }
        throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Não foi possível deletá-lo.");
    }
}
