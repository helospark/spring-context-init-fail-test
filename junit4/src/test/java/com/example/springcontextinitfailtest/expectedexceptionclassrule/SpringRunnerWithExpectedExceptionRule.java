package com.example.springcontextinitfailtest.expectedexceptionclassrule;

import java.util.List;

import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SpringRunnerWithExpectedExceptionRule extends SpringJUnit4ClassRunner {

    public SpringRunnerWithExpectedExceptionRule(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected Statement methodBlock(FrameworkMethod frameworkMethod) {
        List<ExpectedException> testRules = getTestClass().getAnnotatedFieldValues(null, ExpectedExceptionClassRule.class, ExpectedException.class);
        Statement result = super.methodBlock(frameworkMethod);
        for (TestRule item : testRules) {
            result = item.apply(result, getDescription());
        }
        return result;
    }

}
