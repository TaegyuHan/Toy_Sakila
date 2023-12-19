package com.toy.sakila.staff.adapter.out.persistence;

import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.staff.application.port.out.StaffReadPort;
import com.toy.sakila.staff.application.port.out.StaffSavePort;
import com.toy.sakila.staff.domain.Staff;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class StaffPersistenceAdapter implements StaffSavePort, StaffReadPort {

    private final StaffSpringDataJpaRepository staffSpringDataJpaRepository;
    private final StaffPersistenceMapper mapper;

    @Override
    public Staff findById(Staff.StaffId id) {
        return staffSpringDataJpaRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}