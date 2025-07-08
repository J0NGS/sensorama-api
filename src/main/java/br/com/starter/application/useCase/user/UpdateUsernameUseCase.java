package br.com.starter.application.useCase.user;

import br.com.starter.application.api.user.dto.UserResponse;
import br.com.starter.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateUsernameUseCase {
    private final UserService userService;

    public UserResponse execute(UUID userId, String newUsername) {
        return userService.updateUsername(userId, newUsername);
    }
}
