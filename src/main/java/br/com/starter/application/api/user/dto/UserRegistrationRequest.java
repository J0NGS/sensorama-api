package br.com.starter.application.api.user.dto;

import br.com.starter.domain.user.UserStatus;
import lombok.Data;
import java.time.LocalDate;

public record UserRegistrationRequest(
        String username,
        String password,
        String name,
        String gender,
        String phone,
        String document,
        LocalDate birthDate,
        UserStatus status,
        String role) {

    public UserRegistrationRequest withRole(String role) {
        return new UserRegistrationRequest(
                this.username(), this.password(), this.name(),
                this.gender(), this.phone(), this.document(),
                this.birthDate(), this.status(), role);
    }
}