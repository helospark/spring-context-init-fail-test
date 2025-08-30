package com.example.springcontextinitfailtest.jupiterexceptionassert;

import org.assertj.core.api.ThrowingConsumer;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface ExpectedStartupExceptionValidator {

  Class<? extends ThrowingConsumer<Throwable>> value();

}
