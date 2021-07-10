package com.wowls.darcrew.support.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { })
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@ReportAsSingleViolation
@NotNull
@Size(max = 100)
@Pattern(regexp = "[a-z]+[a-z_]*[a-z]*") // 패턴 수정 필요
public @interface ValidProviderName {
    String message() default "validation is failed : provider name must be lower-underscore format like provider_name";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
