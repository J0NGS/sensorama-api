package br.com.starter.application.useCase.userBadge;

import br.com.starter.domain.round.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllUserBadgeUseCase {
    private final UserBadgeService userBadgeService;

    public Page<Round> execute(Pageable pageable) {
        return userBadgeService.getAll(pageable);
    }

}
