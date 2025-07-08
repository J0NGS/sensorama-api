package br.com.starter.application.useCase.privilege;

import br.com.starter.domain.privilege.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeletePrivilegeUseCase {
    private final PrivilegeService privilegeService;

    public boolean execute(UUID privilegeId) {
        return privilegeService.deletePrivilege(privilegeId);
    }
}
