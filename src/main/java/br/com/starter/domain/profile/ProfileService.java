package br.com.starter.domain.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.starter.domain.user.UserStatus;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile findById(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado"));
    }

    public List<Profile> findByNameContainingIgnoreCase(String name) {
        return profileRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Profile> findByUsernameContainingIgnoreCase(String username) {
        return profileRepository.findByUsernameContainingIgnoreCase(username);
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public void deleteById(UUID id) {
        if (!profileRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Perfil não encontrado");
        }

        Optional<Profile> profileOpt = profileRepository.findById(id);
        if (profileOpt.isPresent()) {
            profileRepository.deleteById(id);
            profileOpt.get().setDeletedAt(LocalDateTime.now());
        }
        throw new FrontDisplayableException(
                HttpStatus.NOT_MODIFIED, "Não foi possível deletar o perfil.");
    }
}
