package br.com.starter.application.useCase.role;

import br.com.starter.domain.privilege.Privilege;
import br.com.starter.domain.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetPrivilegesForRoleUseCase {
    private final RoleService roleService;

    public List<Privilege> execute(UUID roleId) {
        return roleService.getRoleById(roleId).getPrivileges();
    }
}
