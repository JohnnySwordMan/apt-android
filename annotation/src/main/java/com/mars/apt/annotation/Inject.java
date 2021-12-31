package com.mars.apt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by JohnnySwordMan on 2021/12/31
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface Inject {
}
