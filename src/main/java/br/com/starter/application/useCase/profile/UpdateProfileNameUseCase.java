package br.com.starter.application.useCase.profile;

import br.com.starter.domain.profile.Profile;
import br.com.starter.domain.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateProfileNameUseCase {
    private final ProfileService profileService;

    public Profile execute(UUID profileId, String newName) {
        Profile profile = profileService.findById(profileId);
        profile.setName(newName);
        return profileService.save(profile);
    }
}
