package br.com.starter.application.useCase.userBadge;

import br.com.starter.domain.round.Round;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetAllUserBadgeUseCase {
    private final UserBadgeService userBadgeService;

    public Page<Round> execute(Pageable pageable) {
        return userBadgeService.getAll(pageable);
    }

}
