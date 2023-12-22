package com.toy.sakila.rental.adapter.out.persistence;

import com.toy.sakila.common.Mapper;
import com.toy.sakila.customer.adapter.out.persistence.CustomerPersistenceMapper;
import com.toy.sakila.inventory.adapter.out.persistence.InventoryPersistenceMapper;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.adapter.out.persistence.StaffPersistenceMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Mapper
public class RentalPersistenceMapper {

    private final InventoryPersistenceMapper inventoryPersistenceMapper;
    private final StaffPersistenceMapper staffPersistenceMapper;
    private final CustomerPersistenceMapper customerPersistenceMapper;

    public RentalJpaEntity mapToJpaEntity(Rental domain) {
        return RentalJpaEntity.builder()
                .rentalId(domain.getId().getValue())
                .rentalDate(domain.getRentalDate())
                .returnDate(domain.getReturnDate())
                .inventory(inventoryPersistenceMapper.mapToJpaEntity(domain.getInventory()))
                .customer(customerPersistenceMapper.mapToJpaEntity(domain.getCustomer()))
                .staff(staffPersistenceMapper.mapToJpaEntity(domain.getStaff()))
                .build();
    }

    public Rental mapToDomainEntity(RentalJpaEntity domain) {
        return Rental.builder()
                .id(Rental.RentalId.of(domain.getRentalId()))
                .rentalDate(domain.getRentalDate())
                .returnDate(domain.getReturnDate())
                .inventory(inventoryPersistenceMapper.mapToDomainEntity(domain.getInventory()))
                .customer(customerPersistenceMapper.mapToDomainEntity(domain.getCustomer()))
                .staff(staffPersistenceMapper.mapToDomainEntity(domain.getStaff()))
                .build();
    }
}