package com.toy.sakila.payment.application.port.out;

import com.toy.sakila.payment.domain.Payment;

public interface PaymentReadPort {
    Payment findById(Payment.PaymentId id);
}
