package com.example.springcontextinitfailtest;

import com.example.springcontextinitfailtest.jupiterexceptionassert.ExpectedStartupExceptionValidator;
import org.assertj.core.api.ThrowingConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.ExpectedStartupExceptionSpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ExpectedStartupExceptionSpringExtension.class) // must be first annotation!
@SpringBootTest
@ExpectedStartupExceptionValidator(ContextFailsUsingValidatorTest.StartupExceptionValidator.class)
public class ContextFailsUsingValidatorTest {

    @Test
    public void contextFails() {
        // This test expects the Spring context to fail during initialization
        // The StartupExceptionValidator predicate will validate that the exception
        // root cause is NoSuchBeanDefinitionException for bean with @Qualifier("NotExists")
    }

    public static class StartupExceptionValidator implements ThrowingConsumer<Throwable> {

        @Override
        public void acceptThrows(Throwable e) {
          assertThat(e)
            .rootCause()
            .isInstanceOf(NoSuchBeanDefinitionException.class)
            .hasMessageContaining("@org.springframework.beans.factory.annotation.Qualifier(\"NotExists\")");
        }

    }
}
