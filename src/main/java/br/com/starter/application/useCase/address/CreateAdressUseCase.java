package br.com.starter.application.useCase.address;

import br.com.starter.application.api.badge.dto.BadgeRegistrationRequest;
import br.com.starter.application.api.common.AddressDTO;
import br.com.starter.domain.address.Address;
import br.com.starter.domain.address.AddressService;
import br.com.starter.domain.badge.Badge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateAdressUseCase {
    private final AddressService addressService;

    public Address execute(AddressDTO request) {
        Address address = new Address();
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setState(request.getState());

        return addressService.create(address);

    }

}
