package br.com.starter.application.useCase.profile;

import br.com.starter.domain.profile.Profile;
import br.com.starter.domain.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProfileUseCase {
    private final ProfileService profileService;

    public Profile execute(Profile profile) {
        return profileService.save(profile);
    }
}
