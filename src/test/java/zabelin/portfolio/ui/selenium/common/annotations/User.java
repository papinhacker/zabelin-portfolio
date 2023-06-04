package com.weedmaps.ui.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface User {
    String className() default "";

    String role() default "";

    String login() default "";

    String password() default "";

    String email() default "";
}
