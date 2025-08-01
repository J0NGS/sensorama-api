package br.com.starter.application.useCase.profile;

import br.com.starter.domain.profile.Profile;
import br.com.starter.domain.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCurrentUserProfileUseCase {
    private final ProfileService profileService;

    public Profile execute(UUID profileId) {
        return profileService.findById(profileId);
    }
}
