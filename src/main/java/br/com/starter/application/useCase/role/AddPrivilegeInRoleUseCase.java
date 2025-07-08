package br.com.starter.application.useCase.role;

import br.com.starter.domain.role.Role;
import br.com.starter.domain.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddPrivilegeInRoleUseCase {
    private final RoleService roleService;

    public Role execute(UUID roleId, List<UUID> privilegeIds) {
        return roleService.addPrivilegesToRole(roleId, privilegeIds);
    }
}
