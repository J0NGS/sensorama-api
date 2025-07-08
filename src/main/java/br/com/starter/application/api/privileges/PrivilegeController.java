package br.com.starter.application.api.privileges;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.privilege.CreatePrivilegeUseCase;
import br.com.starter.application.useCase.privilege.DeletePrivilegeUseCase;
import br.com.starter.application.useCase.privilege.GetAllPrivilegesUseCase;
import br.com.starter.application.useCase.privilege.UpdatePrivilegeAssigntureUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/privileges")
@RequiredArgsConstructor
public class PrivilegeController {

    private final CreatePrivilegeUseCase createPrivilegeUseCase;
    private final DeletePrivilegeUseCase deletePrivilegeUseCase;
    private final GetAllPrivilegesUseCase getAllPrivilegesUseCase;
    private final UpdatePrivilegeAssigntureUseCase updatePrivilegeUseCase;


    @PostMapping
    public ResponseEntity<?> createPrivilege(@RequestParam String name) {
        ResponseDTO<?> response = new ResponseDTO<>(createPrivilegeUseCase.execute(name));
        return ResponseEntity.ok(response);    
    }

    @GetMapping
    public ResponseEntity<?> getAllPrivileges(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllPrivilegesUseCase.execute(pageable));
        return ResponseEntity.ok(response);    
    }

    @PatchMapping("/{id}/signature-revoked")
    public ResponseEntity<?> updateIsSignatureRevoked(@PathVariable UUID id, @RequestParam boolean isSignatureRevoked) {
        ResponseDTO<?> response = new ResponseDTO<>(updatePrivilegeUseCase.execute(id, isSignatureRevoked));
        return ResponseEntity.ok(response);    
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrivilege(@PathVariable UUID id) {
        ResponseDTO<?> response = new ResponseDTO<>(deletePrivilegeUseCase.execute(id));
        return ResponseEntity.ok(response);
    }
}

