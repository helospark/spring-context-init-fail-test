package com.example.springcontextinitfailtest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springcontextinitfailtest.expectedexceptionclassrule.ExpectedExceptionClassRule;
import com.example.springcontextinitfailtest.expectedexceptionclassrule.SpringRunnerWithExpectedExceptionRule;

@RunWith(SpringRunnerWithExpectedExceptionRule.class)
@SpringBootTest
public class ContextFailsUsingExpectedException {
    @ExpectedExceptionClassRule
    public static ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void before() {
        expectedException.expect(IllegalStateException.class);
    }

    @Test
    public void contextFails() {
    }

}
