package com.toy.sakila.actor.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface SpringDataActorRepository extends JpaRepository<ActorJpaEntity, Long> {
    List<ActorJpaEntity> findByIdIn(List<Long> ids);
}