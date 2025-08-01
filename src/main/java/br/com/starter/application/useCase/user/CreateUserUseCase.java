package br.com.starter.application.useCase.user;

import br.com.starter.application.api.user.dto.UserRegistrationRequest;
import br.com.starter.domain.address.Address;
import br.com.starter.domain.address.AddressService;
import br.com.starter.domain.auth.Auth;
import br.com.starter.domain.profile.Profile;
import br.com.starter.domain.role.Role;
import br.com.starter.domain.role.RoleService;
import br.com.starter.domain.user.User;
import br.com.starter.domain.user.UserService;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import br.com.starter.infrastructure.services.utils.StringSanitizer;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserService userService;
    private final RoleService roleService;
    private final AddressService addressService;

    public User execute(UserRegistrationRequest request) {
        
        if (request.status() != null) {
            Profile profile = new Profile();
            profile.setName(request.name());
            profile.setPhone(request.phone());
            profile.setBirthDate(request.birthDate());
            Address addressRequest = new Address();
            addressRequest.setCity(request.city());
            addressRequest.setState(request.state());
            addressRequest.setCountry(request.country());
            addressRequest = addressService.create(addressRequest);
            Auth auth = new Auth();
            auth.setUsername(StringSanitizer.sanitizeString(request.username()));
            auth.setPassword(request.password());
            Role role = roleService.getRoleByName(request.role());
            User user = userService.create(request.status(), role, profile, auth);
            profile.setAddress(addressRequest);
            return user;
        } else
            throw new FrontDisplayableException(HttpStatus.BAD_REQUEST, "Status cannot be null");
    }
}
