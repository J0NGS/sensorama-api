package br.com.starter.application.useCase.address;

import br.com.starter.domain.address.Address;
import br.com.starter.domain.address.AddressService;
import br.com.starter.domain.badge.Badge;
import br.com.starter.domain.badge.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class GetAllAddressUseCase {
    private final AddressService addressService;
    public List<Address> execute() {
        return addressService.getAll();
    }

}
