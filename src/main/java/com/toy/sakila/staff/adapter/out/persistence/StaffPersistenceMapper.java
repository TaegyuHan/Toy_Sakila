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
                .createDate(entity.getCreateDate())
                .build();
    }

    public StaffJpaEntity mapToJpaEntity(Staff domain) {
        return StaffJpaEntity.builder()
                .staffId(domain.getId().getValue())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .email(domain.getEmail())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .active(domain.isActive())
                .build();
    }
}
