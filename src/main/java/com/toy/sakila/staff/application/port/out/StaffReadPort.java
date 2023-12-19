package com.toy.sakila.staff.application.port.out;

import com.toy.sakila.staff.domain.Staff;

public interface StaffReadPort {
    Staff findById(Staff.StaffId id);
}
