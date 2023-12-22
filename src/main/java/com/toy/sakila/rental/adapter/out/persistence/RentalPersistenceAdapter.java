package com.toy.sakila.rental.adapter.out.persistence;

import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.rental.application.port.out.RentalReadPort;
import com.toy.sakila.rental.application.port.out.RentalSavePort;
import com.toy.sakila.rental.domain.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@PersistenceAdapter
@Transactional
public class RentalPersistenceAdapter implements RentalReadPort, RentalSavePort {

    private final SpringDataRentalRepository springDataRentalRepository;
    private final RentalPersistenceMapper mapper;

    @Override
    public Rental save(Rental rental) {
        return Optional.of(rental)
                .map(mapper::mapToJpaEntity)
                .map(springDataRentalRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public Rental findById(Rental.RentalId id) {
        return springDataRentalRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}