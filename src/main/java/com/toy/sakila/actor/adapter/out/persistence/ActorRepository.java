package com.toy.sakila.actor.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ActorRepository extends JpaRepository<ActorJpaEntity, Long> {
}