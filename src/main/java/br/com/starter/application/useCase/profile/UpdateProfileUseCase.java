package br.com.starter.application.useCase.profile;

import br.com.starter.application.api.profile.dto.UpdateProfileRequest;
import br.com.starter.domain.profile.Profile;
import br.com.starter.domain.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateProfileUseCase {
    private final ProfileService profileService;

    public Profile execute(UUID profileId, UpdateProfileRequest request) {
        Profile profile = profileService.findById(profileId);
        
        if (request.getName() != null) {
            profile.setName(request.getName());
        }
        
        if (request.getPhone() != null) {
            profile.setPhone(request.getPhone());
        }
        
        if (request.getBirthDate() != null) {
            profile.setBirthDate(request.getBirthDate());
        }
        
        if (request.getPhoto() != null) {
            profile.setPhoto(request.getPhoto());
        }
        
        return profileService.save(profile);
    }
}
