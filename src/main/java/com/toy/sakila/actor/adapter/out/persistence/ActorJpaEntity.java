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
@Table(
        name = "actor",
        indexes = {
                @Index(name = "idx_actor_last_name", columnList = "last_name")
})
public class ActorJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short actorId;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
}