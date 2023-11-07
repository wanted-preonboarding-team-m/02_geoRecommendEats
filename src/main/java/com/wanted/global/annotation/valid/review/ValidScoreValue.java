package com.wanted.global.annotation.valid.review;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 커스텀 Validator 어노테이션
 * 스코어가 1.0 1.5... 5.0 까지 0.5 단위로 1~5 사이로 오는지 검증
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScoreValueValidator.class)
public @interface ValidScoreValue {

  String message() default "ScoreValidFail";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}