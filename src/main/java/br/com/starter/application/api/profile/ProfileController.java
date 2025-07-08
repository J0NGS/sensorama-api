package br.com.starter.application.api.profile;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.profile.UpdateProfileNameUseCase;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final UpdateProfileNameUseCase updateProfileNameUseCase;

    @PatchMapping("/{profileId}/update-name")
    public ResponseEntity<?> updateNameForProfile(@PathVariable UUID profileId, @RequestParam String newName) {
        ResponseDTO<?> response = new ResponseDTO<>(updateProfileNameUseCase.execute(profileId, newName));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update-name")
    public ResponseEntity<?> updateName(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam String newName) {
        UUID profileId = userDetails.getUser().getProfile().getId();
        ResponseDTO<?> response = new ResponseDTO<>(updateProfileNameUseCase.execute(profileId, newName));
        return ResponseEntity.ok(response);
    }
}
