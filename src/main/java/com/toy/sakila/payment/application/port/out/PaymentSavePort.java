package com.toy.sakila.payment.application.port.out;

import com.toy.sakila.payment.domain.Payment;

public interface PaymentSavePort {
    Payment save(Payment domain);
}
