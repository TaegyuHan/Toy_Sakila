package com.toy.sakila.staff.application.port.out;

import com.toy.sakila.staff.domain.Staff;

public interface StaffSavePort {
    Staff save(Staff staff);
}