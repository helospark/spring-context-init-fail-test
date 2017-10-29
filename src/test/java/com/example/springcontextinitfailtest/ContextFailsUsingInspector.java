package com.example.springcontextinitfailtest;

import java.util.function.Predicate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springcontextinitfailtest.ContextFailsUsingInspector.ExceptionInspector;
import com.example.springcontextinitfailtest.junitexpectedexceptionwithinspector.ExpectedStartupExceptionWithInspector;
import com.example.springcontextinitfailtest.junitexpectedexceptionwithinspector.SpringRunnerWithGlobalExpectedExceptionInspected;

@RunWith(SpringRunnerWithGlobalExpectedExceptionInspected.class)
@SpringBootTest
@ExpectedStartupExceptionWithInspector(ExceptionInspector.class)
public class ContextFailsUsingInspector {

    @Test
    public void contextFails() {
    }

    public static class ExceptionInspector implements Predicate<Throwable> {

        @Override
        public boolean test(Throwable throwable) {
            return throwable instanceof IllegalStateException
                    && throwable.getCause() instanceof BeanCreationException;
        }

    }
}
