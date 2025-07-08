package br.com.starter.application.useCase.privilege;

import br.com.starter.domain.privilege.Privilege;
import br.com.starter.domain.privilege.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePrivilegeUseCase {
    private final PrivilegeService privilegeService;

    public Privilege execute(String privilegeName) {
        return privilegeService.createPrivilege(privilegeName);
    }
}
