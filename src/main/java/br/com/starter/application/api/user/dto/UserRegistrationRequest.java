package br.com.starter.application.api.user.dto;

import br.com.starter.domain.address.Address;
import br.com.starter.domain.user.UserStatus;
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
        String country,
        String state,
        String city,
        String role) {

    public UserRegistrationRequest withRole(String role) {
        return new UserRegistrationRequest(
                this.username(), this.password(), this.name(),
                this.gender(), this.phone(), this.document(),
                this.birthDate(), this.status(), this.country(), this.state(), this.city(), role);
    }
}