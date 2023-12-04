package com.toy.sakila.actor.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ActorRepository extends JpaRepository<ActorJpaEntity, Long> {
    List<ActorJpaEntity> findByIdIn(Set<Long> ids);
}