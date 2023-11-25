package com.toy.sakila.actor.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ActorRepository extends JpaRepository<ActorJpaEntity, Long> {
}