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
@Pattern(regexp = "[A-Z0-9]+[A-Z0-9_]*[A-Z0-9]+") // 패턴 수정 필요
public @interface ValidPageName {
    String message() default "validation is failed : page name must be upper and underscoure format like PAGE_NAME";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
