package com.example.springcontextinitfailtest.expectedexceptionclassrule;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.ClassRule;

/**
 * Annotate an ExpectedException to check startup errors.
 * Unfortunately {@link ClassRule} cannot be used, because it will be picked
 * up by JUnit at the wrong time.
 */
@Retention(RUNTIME)
@Target({ FIELD })
public @interface ExpectedExceptionClassRule {

}
