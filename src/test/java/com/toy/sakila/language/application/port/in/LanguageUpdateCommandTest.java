package com.toy.sakila.language.application.port.in;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LanguageUpdateCommandTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("성공 | Command | Language | 데이터 검증 | 정상적인 데이터")
    void test_successLanguageUpdateCommand() {
        // given
        LanguageUpdateCommand command = LanguageUpdateCommand.builder()
                .name("test")
                .build();

        // when
        Set<ConstraintViolation<LanguageUpdateCommand>> validations = validator.validate(command);

        // then
        assertThat(validations)
                .as("모든 데이터가 정상적으로 있는 경우")
                .isEmpty();
    }

    @Test
    @DisplayName("실패 | Command | Language | 데이터 검증 | name 없을 경우")
    void test_failLanguageUpdateCommand_NoFirstName() {
        // given
        LanguageUpdateCommand command = LanguageUpdateCommand.builder()
                .build();

        // when
        Set<ConstraintViolation<LanguageUpdateCommand>> validations = validator.validate(command);

        // then
        assertThat(validations.stream()
                .anyMatch(violation -> "name".equals(violation.getPropertyPath().toString())))
                .as("name가 없는 경우")
                .isTrue();
    }
}