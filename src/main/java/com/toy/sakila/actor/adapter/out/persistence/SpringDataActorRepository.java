package com.toy.sakila.actor.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SpringDataActorRepository extends JpaRepository<ActorJpaEntity, Short> {
    List<ActorJpaEntity> findByActorIdIn(List<Short> ids);
}