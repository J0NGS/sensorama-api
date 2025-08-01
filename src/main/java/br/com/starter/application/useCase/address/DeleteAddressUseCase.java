package br.com.starter.application.useCase.address;

import br.com.starter.domain.address.AddressService;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
@RequiredArgsConstructor
@Component
public class DeleteAddressUseCase {
    private final AddressService addressService;
    public boolean execute(UUID addressId) {
        try {
            addressService.delete(addressId);
            return true;
        } catch (Exception e) {
            throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Não foi possível deletá-lo.");
        }
    }
}
