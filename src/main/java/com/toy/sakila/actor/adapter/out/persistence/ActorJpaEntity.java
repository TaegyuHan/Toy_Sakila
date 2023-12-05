package com.toy.sakila.actor.adapter.out.persistence;


import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actor")
public class ActorJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
}