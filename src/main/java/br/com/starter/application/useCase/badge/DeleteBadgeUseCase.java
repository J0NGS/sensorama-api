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

    public void execute(UUID badgeId) {
        try {
            badgeService.deleteBadgeById(badgeId);
        } catch (FrontDisplayableException e) {
            throw e;
        } catch (Exception e) {
            throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Não foi possível deletá-lo.");
        }
    }
}
