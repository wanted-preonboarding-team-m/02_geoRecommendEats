package com.wanted.global.annotation.valid.review;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 커스텀 Validator
 * 스코어가 1.0 1.5... 5.0 까지 0.5 단위로 1~5 사이로 오는지 검증
 */
public class ScoreValueValidator implements ConstraintValidator<ValidScoreValue, Double> {

  @Override
  public void initialize(ValidScoreValue constraintAnnotation) {
  }

  @Override
  public boolean isValid(Double value, ConstraintValidatorContext context) {

    return value == 1.0 || value == 1.5 || value == 2.0 || value == 2.5 || value == 3.0 ||
        value == 3.5 || value == 4.0 || value == 4.5 || value == 5.0;
  }
}