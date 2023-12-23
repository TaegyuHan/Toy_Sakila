package com.toy.sakila.customer.domain;

import com.toy.sakila.address.domain.Address;
import com.toy.sakila.store.domain.Store;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Customer {

        private final CustomerId id;
        private Store store;
        private String firstName;
        private String lastName;
        private String email;
        private Address address;
        private boolean active;
        private LocalDateTime createDate;
        private LocalDateTime lastUpdate;

        @Value
        @AllArgsConstructor(staticName = "of")
        public static class CustomerId {
            Short value;
        }
}
