package br.com.starter.application.api.profile;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.api.profile.dto.UpdateProfileRequest;
import br.com.starter.application.useCase.profile.GetCurrentUserProfileUseCase;
import br.com.starter.application.useCase.profile.GetProfileByIdUseCase;
import br.com.starter.application.useCase.profile.UpdateProfileNameUseCase;
import br.com.starter.application.useCase.profile.UpdateProfileUseCase;
import br.com.starter.domain.profile.Profile;
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
    private final GetCurrentUserProfileUseCase getCurrentUserProfileUseCase;
    private final GetProfileByIdUseCase getProfileByIdUseCase;
    private final UpdateProfileUseCase updateProfileUseCase;

    /**
     * Busca o perfil do usuário autenticado
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UUID profileId = userDetails.getUser().getProfile().getId();
        Profile profile = getCurrentUserProfileUseCase.execute(profileId);
        ResponseDTO<Profile> response = new ResponseDTO<>(profile);
        return ResponseEntity.ok(response);
    }

    /**
     * Busca perfil por ID específico
     */
    @GetMapping("/{profileId}")
    public ResponseEntity<?> getProfileById(@PathVariable UUID profileId) {
        Profile profile = getProfileByIdUseCase.execute(profileId);
        ResponseDTO<Profile> response = new ResponseDTO<>(profile);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza o perfil completo do usuário autenticado
     */
    @PutMapping("/me")
    public ResponseEntity<?> updateCurrentUserProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UpdateProfileRequest request) {
        UUID profileId = userDetails.getUser().getProfile().getId();
        Profile updatedProfile = updateProfileUseCase.execute(profileId, request);
        ResponseDTO<Profile> response = new ResponseDTO<>(updatedProfile);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza perfil completo por ID (admin)
     */
    @PutMapping("/{profileId}")
    public ResponseEntity<?> updateProfileById(
            @PathVariable UUID profileId,
            @RequestBody UpdateProfileRequest request) {
        Profile updatedProfile = updateProfileUseCase.execute(profileId, request);
        ResponseDTO<Profile> response = new ResponseDTO<>(updatedProfile);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza nome de perfil específico (admin)
     */
    @PatchMapping("/{profileId}/update-name")
    public ResponseEntity<?> updateNameForProfile(@PathVariable UUID profileId, @RequestParam String newName) {
        Profile updatedProfile = updateProfileNameUseCase.execute(profileId, newName);
        ResponseDTO<Profile> response = new ResponseDTO<>(updatedProfile);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza nome do perfil do usuário autenticado
     */
    @PatchMapping("/update-name")
    public ResponseEntity<?> updateName(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam String newName) {
        UUID profileId = userDetails.getUser().getProfile().getId();
        Profile updatedProfile = updateProfileNameUseCase.execute(profileId, newName);
        ResponseDTO<Profile> response = new ResponseDTO<>(updatedProfile);
        return ResponseEntity.ok(response);
    }
}
