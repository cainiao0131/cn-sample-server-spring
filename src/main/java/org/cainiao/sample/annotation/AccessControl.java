package org.cainiao.sample.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessControl {

    @AliasFor("permissions")
    String[] value() default {};

    /**
     * 访问接口需要的权限<br />
     * 无论
     */
    String[] permissions() default {};
}
