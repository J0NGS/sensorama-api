package br.com.starter.application.api.user;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.api.user.dto.AuthRequestDTO;
import br.com.starter.application.api.user.dto.UserRegistrationRequest;
import br.com.starter.application.useCase.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.starter.domain.user.UserService;

@RestController
@RequestMapping("/sensorama/api/public/users")
@RequiredArgsConstructor
public class UserControllerPublic {
    private final UserService userService;
    private final CreateUserUseCase createUserUseCase;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest user) {
        ResponseDTO<?> response = new ResponseDTO<>(createUserUseCase.execute(user));
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        ResponseDTO<?> response = new ResponseDTO<>(userService.login(authRequest.username(), authRequest.password()));
        return ResponseEntity.ok(response);
    }
}
