package br.com.starter.domain.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    boolean existsByCountryAndCityAndState(
            String country,
            String city,
            String state
    );

    Optional<Address> findByCountryAndCityAndState(
            String country,
            String city,
            String state
    );

    Page<Address> findByCityIgnoreCaseContaining(String city, Pageable pageable);

    Page<Address> findByStateIgnoreCaseContaining(String state, Pageable pageable);
}