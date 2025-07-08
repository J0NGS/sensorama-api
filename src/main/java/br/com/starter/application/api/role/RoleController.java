package br.com.starter.application.api.role;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.role.AddPrivilegeInRoleUseCase;
import br.com.starter.application.useCase.role.GetPrivilegesForRoleUseCase;
import br.com.starter.application.useCase.role.RemovePrivilegeForRoleUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final AddPrivilegeInRoleUseCase addPrivilegeInRoleUseCase;
    private final RemovePrivilegeForRoleUseCase removePrivilegeForRoleUseCase;
    private final GetPrivilegesForRoleUseCase getPrivilegesForRoleUseCase;


    @GetMapping("/{id}/privileges")
    public ResponseEntity<?> getPrivilegesForRole(@PathVariable UUID id) {
        ResponseDTO<?> response = new ResponseDTO<>(getPrivilegesForRoleUseCase.execute(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/privileges")
    public ResponseEntity<?> addPrivilegesToRole(@PathVariable UUID id, @RequestBody List<UUID> privilegeIds) {
        ResponseDTO<?> response = new ResponseDTO<>(addPrivilegeInRoleUseCase.execute(id, privilegeIds));
        return ResponseEntity.ok(response); 
    }

    @DeleteMapping("/{id}/privileges")
    public ResponseEntity<?> removePrivilegesFromRole(@PathVariable UUID id, @RequestBody List<UUID> privilegeIds) {
        ResponseDTO<?> response = new ResponseDTO<>(removePrivilegeForRoleUseCase.execute(id, privilegeIds));
        return ResponseEntity.ok(response);
    }
}
