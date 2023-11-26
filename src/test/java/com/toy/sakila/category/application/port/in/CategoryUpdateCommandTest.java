package com.toy.sakila.category.application.port.in;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryUpdateCommandTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void setup() {
        factory = jakarta.validation.Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("성공 | Command | Category | 데이터 검증 | 정상적인 데이터")
    void test_successCategoryUpdateCommand() {
        // given
        CategoryUpdateCommand command = CategoryUpdateCommand.builder()
                .name("testUpdate")
                .build();

        // when
        Set<ConstraintViolation<CategoryUpdateCommand>> validations = validator.validate(command);

        // then
        assertThat(validations)
                .as("모든 데이터가 정상적으로 있는 경우")
                .isEmpty();
    }

    @Test
    @DisplayName("실패 | Command | Category | 데이터 검증 | name 없을 경우")
    void test_failCategoryUpdateCommand_NoFirstName() {
        // given
        CategoryUpdateCommand command = CategoryUpdateCommand.builder()
                .build();

        // when
        Set<ConstraintViolation<CategoryUpdateCommand>> validations = validator.validate(command);

        // then
        assertThat(validations.stream()
                .anyMatch(violation -> "name".equals(violation.getPropertyPath().toString())))
                .as("name가 없는 경우")
                .isTrue();
    }


}