package com.toy.sakila.payment.application.port.in;

import com.toy.sakila.payment.domain.Payment;

public interface PaymentUpdateUseCase {
    Payment update(Payment.PaymentId id, PaymentUpdateCommand command);
}
