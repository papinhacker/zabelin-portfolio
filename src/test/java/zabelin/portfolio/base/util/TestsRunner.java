package zabelin.portfolio.base.util;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class TestsRunner {

    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add(args[0]);
        testng.setTestSuites(suites);
        testng.run();
    }
}