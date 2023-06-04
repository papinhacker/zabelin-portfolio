package zabelin.portfolio.ui.selenium.common.annotations;

import org.testng.annotations.Test;
import zabelin.portfolio.ui.selenium.common.resourcespool.CommonResourcesPool;

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

    public static void parseResourceAnnotation(Method method, String testIdentity, CommonResourcesPool pool) {
//        Annotation[] annotations = method.getAnnotations();
//        if (annotations.length != 0) {
//            AfterResourceRelease afterResourceRelease = Arrays.stream(annotations).filter(a -> a instanceof AfterResourceRelease).map(a -> (AfterResourceRelease)a).findFirst().orElse(null);
//            if (afterResourceRelease != null) {
//                Operations[] operations = afterResourceRelease.operations();
//                if (operations.length != 0)
//                    for (int i = 0; i < operations.length; i++)
//                        pool.pushAfterResources(testIdentity, operations);
//            }
//
//            BeforeResourceTake beforeResourceTake = Arrays.stream(annotations).filter(a -> a instanceof BeforeResourceTake).map(a -> (BeforeResourceTake)a).findFirst().orElse(null);
//            if (beforeResourceTake != null) {
//                Operations[] operations = beforeResourceTake.operations();
//                if (operations.length != 0)
//                    for (int i = 0; i < operations.length; i++)
//                        pool.pushBeforeResources(testIdentity, operations);
//            }
//        }
    }
}
