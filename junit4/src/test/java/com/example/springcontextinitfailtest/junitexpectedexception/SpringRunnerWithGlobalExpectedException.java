package com.example.springcontextinitfailtest.junitexpectedexception;

import org.junit.internal.runners.statements.ExpectException;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SpringRunnerWithGlobalExpectedException extends SpringJUnit4ClassRunner {
    private Class<? extends Throwable> expectedException;

    public SpringRunnerWithGlobalExpectedException(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected TestClass createTestClass(Class<?> clazz) {
        ExpectedStartupException annotation = clazz.getAnnotation(ExpectedStartupException.class);
        if (annotation != null) {
            expectedException = annotation.value();
        } else {
            throw new IllegalArgumentException("Missing " + ExpectedStartupException.class.getName() + " on " + clazz.getName());
        }
        return super.createTestClass(clazz);
    }

    @Override
    protected Statement methodBlock(FrameworkMethod frameworkMethod) {
        Statement result = super.methodBlock(frameworkMethod);
        return new ExpectException(result, expectedException);
    }

}
