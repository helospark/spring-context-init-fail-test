package com.example.springcontextinitfailtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springcontextinitfailtest.junitexpectedexception.ExpectedStartupException;
import com.example.springcontextinitfailtest.junitexpectedexception.SpringRunnerWithGlobalExpectedException;

@RunWith(SpringRunnerWithGlobalExpectedException.class)
@SpringBootTest
@ExpectedStartupException(IllegalStateException.class)
public class ContextFailsUsingAnnotation {

    @Test
    public void contextFails() {
    }

}
