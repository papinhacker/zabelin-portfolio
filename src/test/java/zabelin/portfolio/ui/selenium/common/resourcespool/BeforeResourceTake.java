package zabelin.portfolio.ui.selenium.common.resourcespool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BeforeResourceTake {
    Operations[] operations() default {};
    // Class<?> processor() default Object.class;
}
