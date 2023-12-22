package com.toy.sakila.payment.application.service;

import com.toy.sakila.customer.application.port.out.CustomerReadPort;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.payment.application.port.in.PaymentUpdateCommand;
import com.toy.sakila.payment.application.port.in.PaymentUpdateUseCase;
import com.toy.sakila.payment.application.port.out.PaymentReadPort;
import com.toy.sakila.payment.application.port.out.PaymentSavePort;
import com.toy.sakila.payment.domain.Payment;
import com.toy.sakila.rental.application.port.out.RentalReadPort;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.application.port.out.StaffReadPort;
import com.toy.sakila.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentUpdateService implements PaymentUpdateUseCase {

    private final CustomerReadPort customerReadPort;
    private final StaffReadPort staffReadPort;
    private final RentalReadPort rentalReadPort;
    private final PaymentReadPort paymentReadPort;
    private final PaymentSavePort paymentSavePort;

    @Override
    public Payment update(Payment.PaymentId id, PaymentUpdateCommand command) {
        Payment domain = paymentReadPort.findById(id);
        Customer customer = customerReadPort.findById(Customer.CustomerId.of(command.getCustomerId()));
        Staff staff = staffReadPort.findById(Staff.StaffId.of(command.getStaffId()));
        Rental rental = rentalReadPort.findById(Rental.RentalId.of(command.getRentalId()));

        domain.setCustomer(customer);
        domain.setStaff(staff);
        domain.setRental(rental);

        return paymentSavePort.save(domain);
    }
}