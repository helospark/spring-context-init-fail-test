package com.example.springcontextinitfailtest.junitexpectedexceptionwithinspector;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.Predicate;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface ExpectedStartupExceptionWithInspector {

    Class<? extends Predicate<Throwable>> value();
}
