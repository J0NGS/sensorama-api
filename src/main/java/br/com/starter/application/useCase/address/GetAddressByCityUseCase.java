package br.com.starter.application.useCase.address;

import br.com.starter.domain.address.Address;
import br.com.starter.domain.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAddressByCityUseCase {
    private final AddressService addressService;
    public Page<Address> execute(String city, Pageable pageable) {
        return addressService.searchByCity(city, pageable);
    }

}
