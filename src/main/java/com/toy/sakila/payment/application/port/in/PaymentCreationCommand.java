package com.toy.sakila.payment.application.port.in;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PaymentCreationCommand {
    Short customerId;
    Byte staffId;
    Integer rentalId;
    BigDecimal amount;
    LocalDateTime paymentDate;
}
