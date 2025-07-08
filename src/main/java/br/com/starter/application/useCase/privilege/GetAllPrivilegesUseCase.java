package br.com.starter.application.useCase.privilege;

import br.com.starter.domain.privilege.Privilege;
import br.com.starter.domain.privilege.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllPrivilegesUseCase {
    private final PrivilegeService privilegeService;

    public Page<Privilege> execute(Pageable pageable) {
        return privilegeService.getAllPrivileges(pageable);
    }
}
