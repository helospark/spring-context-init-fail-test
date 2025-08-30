package com.example.springcontextinitfailtest.junitexpectedexceptionwithinspector;

import java.util.function.Predicate;

import org.junit.internal.runners.statements.Fail;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.springcontextinitfailtest.junitexpectedexception.ExpectedStartupException;

public class SpringRunnerWithGlobalExpectedExceptionInspected extends SpringJUnit4ClassRunner {
    private Class<? extends Predicate<Throwable>> expectedExceptionInspector;

    public SpringRunnerWithGlobalExpectedExceptionInspected(Class<?> clazz) throws InitializationError {
        super(clazz);
        ExpectedStartupExceptionWithInspector annotation = clazz.getAnnotation(ExpectedStartupExceptionWithInspector.class);
        if (annotation != null) {
            expectedExceptionInspector = annotation.value();
        } else {
            throw new IllegalArgumentException("Missing " + ExpectedStartupException.class.getName() + " on " + clazz.getName());
        }
    }

    @Override
    protected Statement methodBlock(FrameworkMethod frameworkMethod) {
        Statement result = super.methodBlock(frameworkMethod);
        try {
            return new ExpectExceptionWithPredicate(result, expectedExceptionInspector.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            return new Fail(e);
        }
    }

}
