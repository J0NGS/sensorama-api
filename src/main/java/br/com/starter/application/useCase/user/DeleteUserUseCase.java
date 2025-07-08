package br.com.starter.application.useCase.user;

import br.com.starter.domain.user.UserService;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final UserService userService;

    public boolean execute(UUID userId) {
        if (userService.deleteById(userId)) {
            return true;
        }
        throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Não foi possível deletá-lo.");
    }
}
