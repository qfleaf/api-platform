package com.qfleaf.yunapi.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

import static com.qfleaf.yunapi.annotation.support.AuthConst.ROLE_ADMIN;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorized {
    @AliasFor("authority")
    String[] value() default {ROLE_ADMIN};
    @AliasFor("value")
    String[] authority() default {ROLE_ADMIN};
}
