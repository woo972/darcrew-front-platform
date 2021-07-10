package com.wowls.darcrew.support.validation;


import com.wowls.darcrew.support.validation.annotation.AppVersion;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class AppVersionValidator implements ConstraintValidator<AppVersion, String> {
    private static final Pattern pattern = Pattern.compile("[0-9]+\\.[0-9]+\\.[0-9]+");
    private boolean optional;

    @Override
    public void initialize(AppVersion constraintAnnotation) {
        optional = constraintAnnotation.optional();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (isBlank(value)) {
            return optional;
        }
        return pattern.matcher(value).matches();
    }
}
