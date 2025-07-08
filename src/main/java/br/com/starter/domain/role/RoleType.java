package br.com.starter.domain.role;

import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_DEV("DEV");

    private final String name;

    RoleType(String simpleName) {
        this.name = simpleName;
    }
}
