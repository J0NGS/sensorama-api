package br.com.starter.infrastructure.config.boot;

import br.com.starter.domain.role.Role;
import br.com.starter.domain.role.RoleRepository;
import br.com.starter.domain.role.RoleService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class DatabaseInitializerRoles {
    @Bean
    @Order(1)
    public CommandLineRunner initializeRoles(RoleRepository roleRepository, RoleService roleService) {
        return args -> {
            List<String> defaultRoles = List.of("ROLE_DEV", "ROLE_ADMIN", "ROLE_USER");

            for (String roleName : defaultRoles) {
                if (!roleRepository.existsByName(roleName)) {
                    List<UUID> privilegeIds = new ArrayList<>(); // You can set this to null or provide a list of privilege IDs if needed
                    roleService.createRole(roleName, privilegeIds);
                }
            }
        };
    }
}