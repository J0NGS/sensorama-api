package br.com.starter.application.useCase.address;

import br.com.starter.domain.address.Address;
import br.com.starter.domain.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@RequiredArgsConstructor
@Component
public class GetAddressByIdUseCase {
    private final AddressService addressService;
    public Address execute(UUID addressId) {
        return addressService.getById(addressId);
    }
}
