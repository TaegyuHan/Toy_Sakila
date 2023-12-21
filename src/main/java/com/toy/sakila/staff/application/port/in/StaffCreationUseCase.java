package com.toy.sakila.staff.application.port.in;

import com.toy.sakila.staff.domain.Staff;

public interface StaffCreationUseCase {
    Staff create(StaffCreationCommand command);
}
