package br.com.starter.application.useCase.user;

import br.com.starter.application.api.user.dto.UpdateUserDTO;
import br.com.starter.domain.user.User;
import br.com.starter.domain.user.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final UserService userService;

    public User execute(UUID userId, UpdateUserDTO updateUserDTO) {
        return userService.update(userId, updateUserDTO);
    }
}
