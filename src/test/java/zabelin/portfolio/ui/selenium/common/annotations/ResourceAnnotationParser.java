package zabelin.portfolio.ui.selenium.common.annotations;

import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ResourceAnnotationParser {

    public static String getTestIdentity(Method method) {
        Annotation[] annotations = method.getAnnotations();
        if (annotations.length != 0) {
            Test testAnnotation = Arrays.stream(annotations).filter(a -> a instanceof Test).map(a -> (Test) a).findFirst().orElse(null);
            assert testAnnotation != null;
            return testAnnotation.description();
        }

        return null;
    }
}
