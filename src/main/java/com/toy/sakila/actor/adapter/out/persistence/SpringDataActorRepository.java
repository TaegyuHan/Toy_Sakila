package com.toy.sakila.actor.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataActorRepository extends JpaRepository<ActorJpaEntity, Long> {
}