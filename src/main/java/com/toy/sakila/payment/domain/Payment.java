package com.toy.sakila.payment.domain;

import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.domain.Staff;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Payment {
    private PaymentId id;
    private Customer customer;
    private Staff staff;
    private Rental rental;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private LocalDateTime lastUpdate;
    private LocalDateTime createDate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class PaymentId {
        Long value;
    }
}
