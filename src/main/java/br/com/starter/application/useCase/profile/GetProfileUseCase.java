package br.com.starter.application.useCase.profile;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.starter.domain.profile.Profile;
import br.com.starter.domain.profile.ProfileService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetProfileUseCase {
    private final ProfileService profileService;

    public Profile execute(UUID profileId) {
        return profileService.findById(profileId);
        }
}
