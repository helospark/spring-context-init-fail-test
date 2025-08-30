package org.springframework.test.context.junit.jupiter;

import com.example.springcontextinitfailtest.jupiterexceptionassert.ExpectedStartupExceptionValidator;
import org.assertj.core.api.ThrowingConsumer;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * SpringExtension override catching any application context init errors and forwarding them to a validator,
 * provided by {@link ExpectedStartupExceptionValidator} annotation.
 * <p>
 * Unfortunately, Spring Test doesn't provide a way to reach {@link org.springframework.test.context.TestContextManager}
 * from outside {@code org.springframework.test.context.junit.jupiter} package, thus this class can only live there.
 */
public class ExpectedStartupExceptionSpringExtension extends SpringExtension {

  @Override
  public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
    Class<?> testClass = context.getRequiredTestClass();
    ExpectedStartupExceptionValidator annotation = testClass.getAnnotation(ExpectedStartupExceptionValidator.class);
    Class<? extends ThrowingConsumer<Throwable>> validatorClass;
    if (annotation != null) {
      validatorClass = annotation.value();
    } else {
      throw new IllegalArgumentException("Missing " + ExpectedStartupExceptionValidator.class.getName() + " on " + testClass.getName());
    }

    boolean complete = false;
    try {
      super.postProcessTestInstance(testInstance, context);
      complete = true;
    } catch (Throwable e) {
      assertThat(e).satisfies(validatorClass.getDeclaredConstructor().newInstance());
    }
    if (complete) {
      throw new AssertionError("Expected exception was not thrown");
    }

    // prevents following attempts of any SpringExtension instance to load application context
    getTestContextManager(context).getTestExecutionListeners().clear();
  }

}
