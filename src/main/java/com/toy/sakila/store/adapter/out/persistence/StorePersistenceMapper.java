package com.toy.sakila.store.adapter.out.persistence;

import com.toy.sakila.address.adapter.out.persistence.AddressPersistenceMapper;
import com.toy.sakila.common.Mapper;
import com.toy.sakila.staff.adapter.out.persistence.StaffPersistenceMapper;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Mapper
public class StorePersistenceMapper {

    private final AddressPersistenceMapper addressPersistenceMapper;
    private final StaffPersistenceMapper staffPersistenceMapper;

    public Store mapToDomainEntity(StoreJpaEntity entity) {
        return Store.builder()
                .id(Store.StoreId.of(entity.getStoreId()))
                .address(addressPersistenceMapper.mapToDomainEntity(entity.getAddress()))
                .managerStaff(staffPersistenceMapper.mapToDomainEntity(entity.getManagerStaff()))
                .lastUpdate(entity.getLastUpdate())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}