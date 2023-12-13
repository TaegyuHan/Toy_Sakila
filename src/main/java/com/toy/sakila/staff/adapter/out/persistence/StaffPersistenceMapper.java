package com.toy.sakila.staff.adapter.out.persistence;

import com.toy.sakila.common.Mapper;
import com.toy.sakila.staff.domain.Staff;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Mapper
public class StaffPersistenceMapper {
    public Staff mapToDomainEntity(StaffJpaEntity entity) {
        return Staff.builder()
                .id(Staff.StaffId.of(entity.getStaffId()))
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .active(entity.isActive())
                .lastUpdate(entity.getLastUpdate())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
