package br.com.starter.application.api.address;

import br.com.starter.application.api.category.dto.CategoryRegistrationRequest;
import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.application.api.common.AddressDTO;
import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.address.*;
import br.com.starter.domain.address.Address;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/sensorama/api/address")
@RequiredArgsConstructor
public class AddressController {
    private final CreateAdressUseCase createAdressUseCase;
    private final DeleteAddressUseCase deleteAdressUseCase;
    private final UpdateAddressUseCase updateAdressUseCase;
    private final GetAddressByCityUseCase getAddressByCityUseCase;
    private final GetAddressByStateUseCase getAddressByStateUseCase;
    private final GetAllAddressUseCase getAllAddressUseCase;
    private final GetAddressByIdUseCase getAddressbyIdUseCase;

    //index
    @GetMapping
    public ResponseEntity<?> getAll() {
        ResponseDTO<?> response = new ResponseDTO<>(getAllAddressUseCase.execute());
        return ResponseEntity.ok(response);
    }

    //findBy
    @GetMapping("/{addressId}")
    public ResponseEntity<?> findById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("addressId") UUID addressId) {
        ResponseDTO<?> response = new ResponseDTO<>(getAddressbyIdUseCase.execute(addressId));
        return ResponseEntity.ok(response);
    }

    //findBy city
    @PostMapping("/city")
    public ResponseEntity<?> getByCity(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                               Pageable pageable,
                                               @RequestBody String city) {
        ResponseDTO<?> response = new ResponseDTO<>(getAddressByCityUseCase.execute(city, pageable));
        return ResponseEntity.ok(response);
    }

    //findBy state
    @PostMapping("/state")
    public ResponseEntity<?> getByState(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                       Pageable pageable,
                                       @RequestBody String state) {
        ResponseDTO<?> response = new ResponseDTO<>(getAddressByStateUseCase.execute(state, pageable));
        return ResponseEntity.ok(response);
    }

    //create
    @PostMapping
    public ResponseEntity<?> register(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                      @RequestBody AddressDTO address) {
        ResponseDTO<?> response = new ResponseDTO<>(createAdressUseCase.execute(address));
        return ResponseEntity.ok(response);
    }

    //update
    @PutMapping("/{addressId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                    @PathVariable("addressId") UUID addressId, @RequestBody Address request) {
        ResponseDTO<?> response = new ResponseDTO<>(updateAdressUseCase.execute(addressId, request));
        return ResponseEntity.ok(response);
    }

    //delete
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                    @PathVariable UUID addressId) {
        ResponseDTO<?> response = new ResponseDTO<>(deleteAdressUseCase.execute(addressId));
        return ResponseEntity.ok(response);
    }
}
