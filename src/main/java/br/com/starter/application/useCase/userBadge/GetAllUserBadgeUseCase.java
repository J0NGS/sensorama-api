package br.com.starter.application.useCase.userBadge;

import br.com.starter.domain.userBadge.UserBadge;
import br.com.starter.domain.userBadge.UserBadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllUserBadgeUseCase {
    private final UserBadgeService userBadgeService;

    public Page<UserBadge> execute(Pageable pageable) {
        return userBadgeService.getAll(pageable);
    }
}
