package com.wowls.darcrew.support.validation.annotation;

import com.wowls.darcrew.support.validation.AppVersionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Constraint(validatedBy = {AppVersionValidator.class})
@Target({ FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppVersion {
    String message() default "validation failed: invalid app version format. ex) 5.1.3";
    boolean optional() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
