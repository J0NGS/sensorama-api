package br.com.starter.application.useCase.address;

import br.com.starter.application.api.badge.dto.UpdateBadgeDTO;
import br.com.starter.application.api.common.AddressDTO;
import br.com.starter.domain.address.Address;
import br.com.starter.domain.address.AddressService;
import br.com.starter.domain.badge.Badge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UpdateAddressUseCase {
    private final AddressService addressService;
    public Address execute(UUID addressId, Address address) {
        return addressService.update(addressId, address);
    }
}
