package com.example.springcontextinitfailtest.junitexpectedexceptionwithinspector;

import java.util.function.Predicate;

import org.junit.AssumptionViolatedException;
import org.junit.runners.model.Statement;

public class ExpectExceptionWithPredicate extends Statement {
    private Predicate<Throwable> inspector;
    private Statement next;

    public ExpectExceptionWithPredicate(Statement next, Predicate<Throwable> inspector) {
        this.next = next;
        this.inspector = inspector;
    }

    @Override
    public void evaluate() throws Throwable {
        boolean complete = false;
        try {
            next.evaluate();
            complete = true;
        } catch (AssumptionViolatedException e) {
            throw e;
        } catch (Throwable e) {
            if (!inspector.test(e)) {
                String message = "Unexpected exception, " + inspector.getClass().getName() + " returned false";
                throw new Exception(message, e);
            }
        }
        if (complete) {
            throw new AssertionError("Expected exception");
        }
    }
}
