package com.toy.sakila.actor.application.port.in;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ActorCreationCommandTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("성공 | Command | Actor | 데이터 검증 | 정상적인 데이터")
    void test_successActorCreationCommand() {
        // given
        ActorCreationCommand command = ActorCreationCommand.builder()
                .firstName("first name")
                .lastName("last name")
                .build();

        // when
        Set<ConstraintViolation<ActorCreationCommand>> validations = validator.validate(command);

        // then
        assertThat(validations)
                .as("모든 데이터가 정상적으로 있는 경우")
                .isEmpty();
    }

    @Test
    @DisplayName("실패 | Command | Actor | 데이터 검증 | firstName 없을 경우")
    void test_failActorCreationCommand_NoFirstName() {
        // given
        ActorCreationCommand command = ActorCreationCommand.builder()
                .lastName("last name")
                .build();

        // when
        Set<ConstraintViolation<ActorCreationCommand>> validations = validator.validate(command);

        // then
        assertThat(validations.stream()
                .anyMatch(violation -> "firstName".equals(violation.getPropertyPath().toString())))
                .as("firstName가 없는 경우")
                .isTrue();
    }

    @Test
    @DisplayName("실패 | Command | Actor | 데이터 검증 | fastName 없을 경우")
    void test_failActorCreationCommand_NoLastName() {
        // given
        ActorCreationCommand command = ActorCreationCommand.builder()
                .firstName("first name")
                .build();

        // when
        Set<ConstraintViolation<ActorCreationCommand>> validations = validator.validate(command);

        // then
        assertThat(validations.stream()
                .anyMatch(violation -> "lastName".equals(violation.getPropertyPath().toString())))
                .as("lastName가 없는 경우")
                .isTrue();
    }
}