package com.toy.sakila.payment.application.service;

import com.toy.sakila.customer.application.port.out.CustomerReadPort;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.payment.application.port.in.PaymentCreationCommand;
import com.toy.sakila.payment.application.port.in.PaymentCreationUseCase;
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
public class PaymentCreationService implements PaymentCreationUseCase {

    private final CustomerReadPort customerReadPort;
    private final StaffReadPort staffReadPort;
    private final RentalReadPort rentalReadPort;
    private final PaymentSavePort paymentSavePort;

    @Override
    public Payment create(PaymentCreationCommand command) {
        Customer customer = customerReadPort.findById(Customer.CustomerId.of(command.getCustomerId()));
        Staff staff = staffReadPort.findById(Staff.StaffId.of(command.getStaffId()));
        Rental  rental = rentalReadPort.findById(Rental.RentalId.of(command.getRentalId()));
        Payment domain = Payment.builder()
                .customer(customer)
                .staff(staff)
                .rental(rental)
                .amount(command.getAmount())
                .build();

        return paymentSavePort.save(domain);
    }
}
