//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.common;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class AssertVerification {
    protected SoftAssert softAssert;

    public AssertVerification() {
    }

    @BeforeMethod(
            alwaysRun = true
    )
    public void assertVerificationBeforeMethod() {
        this.softAssert = new SoftAssert();
    }

    @AfterMethod(
            alwaysRun = true
    )
    public void assertVerificationAfterMethod() {
        this.softAssert = null;
    }

    public void softAssertTrue(boolean condition, String desc) {
        this.softAssert.assertTrue(condition, desc);
        if (!condition) {
            Log.assertFail(desc);
        }

    }

    public void softAssertFalse(boolean condition, String desc) {
        this.softAssert.assertFalse(condition, desc);
        if (condition) {
            Log.assertFail(desc);
        }

    }

    public void softAssertEquals(Object actual, Object expected, String desc) {
        this.softAssert.assertEquals(actual, expected, desc);
        if (!actual.equals(expected)) {
            Log.assertFail(desc);
        }

    }

    public void softAssertNotEquals(Object actual, Object expected, String desc) {
        this.softAssert.assertNotEquals(actual, expected, desc);
        if (actual.equals(expected)) {
            Log.assertFail(desc);
        }

    }

    public void softAssertContains(String inputString, String searchSubString, String desc) {
        this.softAssert.assertTrue(inputString.contains(searchSubString), desc + "\nActual string = " + inputString + "\nExpected substring = " + searchSubString + "\n");
        if (!inputString.contains(searchSubString)) {
            Log.assertFail(desc);
        }

    }

    public void softAssertNull(Object object, String desc) {
        this.softAssert.assertNull(object, desc);
        if (object != null) {
            Log.assertFail(desc);
        }

    }

    public void softAssertNotNull(Object object, String desc) {
        this.softAssert.assertNotNull(object, desc);
        if (object == null) {
            Log.assertFail(desc);
        }

    }

    public void softAssertNotSame(Object actual, Object expected, String desc) {
        this.softAssert.assertNotSame(actual, expected, desc);
        if (actual != expected) {
            Log.assertFail(desc);
        }

    }

    public void softAssertAll() {
        this.softAssert.assertAll();
    }
}
