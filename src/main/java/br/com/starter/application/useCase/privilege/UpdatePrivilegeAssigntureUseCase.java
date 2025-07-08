package br.com.starter.application.useCase.privilege;

import br.com.starter.domain.privilege.Privilege;
import br.com.starter.domain.privilege.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdatePrivilegeAssigntureUseCase {
    private final PrivilegeService privilegeService;

    public Privilege execute(UUID privilegeId, boolean isSignatureRevoked) {
        return privilegeService.updateIsSignatureRevoked(privilegeId, isSignatureRevoked);
    }
}
