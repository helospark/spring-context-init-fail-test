package com.example.springcontextinitfailtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcontextinitfailtestApplication {
    // Kill the application context init for test
    @Autowired
    @Qualifier("NotExists")
    private String notExistentString;

    public static void main(String[] args) {
        SpringApplication.run(SpringcontextinitfailtestApplication.class, args);
    }
}
