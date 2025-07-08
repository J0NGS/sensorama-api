package br.com.starter.application.api.user;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.api.user.dto.UpdateUserDTO;
import br.com.starter.application.useCase.user.AddPrivilegesToUserUseCase;
import br.com.starter.application.useCase.user.ChangeUserRoleUseCase;
import br.com.starter.application.useCase.user.UpdateUserUseCase;
import br.com.starter.application.useCase.user.UpdateUsernameUseCase;
import br.com.starter.application.useCase.user.DeleteUserUseCase;
import br.com.starter.application.useCase.user.RemovePrivilegesToUserUseCase;
import br.com.starter.application.useCase.user.UpdatePasswordUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.starter.domain.user.CustomUserDetails;

@RestController
@RequestMapping("/sensorama/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUsernameUseCase updateUsernameUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final AddPrivilegesToUserUseCase addPrivilegesToUserUseCase;
    private final RemovePrivilegesToUserUseCase removePrivilegesToUserUseCase;
    private final ChangeUserRoleUseCase changeUserRoleUseCase;

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userAuthentication,
            @PathVariable("userId") UUID userId, @RequestBody UpdateUserDTO request) {
        ResponseDTO<?> response = new ResponseDTO<>(updateUserUseCase.execute(userId, request));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable UUID userId, @RequestParam String newPassword) {
        ResponseDTO<?> response = new ResponseDTO<>(updatePasswordUseCase.execute(userId, newPassword));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/update-username")
    public ResponseEntity<?> updateUsername(@PathVariable UUID userId, @RequestParam String newUsername) {
        ResponseDTO<?> response = new ResponseDTO<>(updateUsernameUseCase.execute(userId, newUsername));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/privileges/{privilegeId}/add")
    public ResponseEntity<?> addPrivilegeToUser(@PathVariable UUID userId, @PathVariable UUID privilegeId) {
        ResponseDTO<?> response = new ResponseDTO<>(addPrivilegesToUserUseCase.execute(userId, privilegeId));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/privileges/{privilegeId}/remove")
    public ResponseEntity<?> removePrivilegeFromUser(@PathVariable UUID userId, @PathVariable UUID privilegeId) {
        ResponseDTO<?> response = new ResponseDTO<>(removePrivilegesToUserUseCase.execute(userId, privilegeId));
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{userId}/update-role/{roleId}")
    public ResponseEntity<?> updateRoleForUser(@PathVariable UUID userId, @PathVariable UUID roleId) {
        ResponseDTO<?> response = new ResponseDTO<>(changeUserRoleUseCase.execute(userId, roleId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        ResponseDTO<?> response = new ResponseDTO<>(deleteUserUseCase.execute(userId));
        return ResponseEntity.ok(response);
    }
}
