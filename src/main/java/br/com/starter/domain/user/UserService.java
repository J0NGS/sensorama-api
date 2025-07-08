package br.com.starter.domain.user;

import br.com.starter.application.api.user.dto.UpdateUserDTO;
import br.com.starter.domain.privilege.Privilege;
import br.com.starter.domain.privilege.PrivilegeRepository;
import br.com.starter.application.api.user.dto.UserResponse;
import br.com.starter.domain.auth.Auth;
import br.com.starter.domain.auth.AuthService;
import br.com.starter.domain.profile.Profile;
import br.com.starter.domain.role.Role;
import br.com.starter.domain.role.RoleRepository;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import br.com.starter.infrastructure.jwt.JwtGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;
    private final AuthService authService;
    private final RoleRepository roleRepository;

    @Transactional
    public String login(String username, String password) {
        try {
            // Realiza autenticação
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            return jwtGenerator.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new FrontDisplayableException(
                    HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }
    }

    @Transactional
    public User save(User user) {
        try {
            // Auth service já faz a verificação do username
            Auth auth = authService.createAuth(user.getAuth().getUsername(), user.getAuth().getPassword()).getBody();
            user.setAuth(auth);
        } catch (FrontDisplayableException e) {
            throw new FrontDisplayableException(
                    HttpStatus.BAD_REQUEST,
                    "Erro ao criar autenticação: " + e.getReason());
        }
        Optional<User> userSaved = Optional.ofNullable(userRepository.save(user));
        
        if (!userSaved.isPresent()) {
            throw new FrontDisplayableException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar usuário!");
        }

        return userSaved.get();
    }

    @Transactional
    public User create(UserStatus status, Role role, Profile profile, Auth auth) {
        User newUser = new User();
        newUser.setStatus(status);
        newUser.setProfile(profile);
        newUser.setAuth(auth);
        newUser.setRole(role);
        return save(newUser);
    }
    
    public User getUserById (UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new FrontDisplayableException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado!"));
    }

    @Transactional
    public UserResponse addPrivilegeToUser(UUID userId, UUID privilegeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        Privilege privilege = privilegeRepository.findById(privilegeId)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Privilégio não encontrado!"));

        if (!user.getPrivileges().contains(privilege)) {
            user.getPrivileges().add(privilege);
            userRepository.save(user);
        }
        
        return UserResponse.fromUser(user);
    }

    @Transactional
    public UserResponse removePrivilegeFromUser(UUID userId, UUID privilegeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        Privilege privilege = privilegeRepository.findById(privilegeId)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Privilégio não encontrado!"));

        if (user.getPrivileges().contains(privilege)) {
            user.getPrivileges().remove(privilege);
            userRepository.save(user);
        } else {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Privilégio não pertence ao usuário!");
        }

        return UserResponse.fromUser(user);
    }

    @Transactional
    public UserResponse updatePassword(UUID userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new FrontDisplayableException(HttpStatus.BAD_REQUEST, "A nova senha não pode ser vazia!");
        }

        authService.updatePassword(user.getAuth().getId(), newPassword);
        return UserResponse.fromUser(user);
    }

    @Transactional
    public UserResponse updateUsername(UUID userId, String newUsername) {
        if (newUsername == null || newUsername.trim().isEmpty()) {
            throw new FrontDisplayableException(HttpStatus.BAD_REQUEST, "O novo nome de usuário não pode ser vazio!");
        }

        if (authService.usernameExists(newUsername) 
                && !userRepository.findByAuthUsername(newUsername).get().getId().equals(userId)) {
            throw new FrontDisplayableException(HttpStatus.CONFLICT, "O nome de usuário já está em uso!");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        authService.updateUsername(user.getAuth().getId(), newUsername);
        return UserResponse.fromUser(user);
    }

    @Transactional
    public boolean deleteById(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            userOpt.get().setStatus(UserStatus.INACTIVE);
            userOpt.get().setDeletedAt(LocalDateTime.now());
            return true;
        }
        return false;
    }

    public User update (
        UUID userId,
        UpdateUserDTO createForm
    ) {
        User currentUser = userRepository.findById(userId).orElseThrow(() ->
            new FrontDisplayableException(
                HttpStatus.BAD_REQUEST,
                "Usuário não encontrado!"
            )
        );

        if(createForm.getStatus() != null)
            currentUser.setStatus(createForm.getStatus());
    
        // update auth
        if(createForm.getPassword() != null)
            updatePassword(currentUser.getId(), createForm.getPassword());
        if(createForm.getEmail() != null)
            updateUsername(currentUser.getId(), createForm.getEmail());

        Profile profile = currentUser.getProfile();
        profile.setName(createForm.getName());
        profile.setPhone(createForm.getPhone());
        profile.setBirthDate(createForm.getBirthDate());
        currentUser.setProfile(profile);

        return userRepository.save(currentUser);
    }

    public boolean usernameExists(String newUsername) {
        return userRepository.findByAuthUsername(newUsername).isPresent();
    }

    public UserResponse assignRoleToUser(UUID userId, UUID roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Função não encontrada!"));

        user.setRole(role);
        userRepository.save(user);
        return UserResponse.fromUser(user);
    }

}
