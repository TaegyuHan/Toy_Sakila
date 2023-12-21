package com.toy.sakila.staff.application.port.in;

import com.toy.sakila.staff.domain.Staff;

public interface StaffUpdateUseCase {
    Staff update(Staff.StaffId id, StaffUpdateCommand command);
}
