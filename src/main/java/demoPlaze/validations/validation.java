package demoPlaze.validations;

import demoPlaze.utiles.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class validation extends BasAssertion {
    
    public validation(WebDriver driver) {
        super(driver);
    }
    private static final ThreadLocal<SoftAssert> softAssertThreadLocal = ThreadLocal.withInitial(SoftAssert::new);
    private static final ThreadLocal<Boolean> usedThreadLocal = ThreadLocal.withInitial(() -> false);

    @Override
    protected void assertTrue(boolean condition, String message) {
        usedThreadLocal.set(true);
        softAssertThreadLocal.get().assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        usedThreadLocal.set(true);
        softAssertThreadLocal.get().assertFalse(condition, message);
    }

    @Override
    protected void assertequal(String actual, String expected, String message) {
        usedThreadLocal.set(true);
        softAssertThreadLocal.get().assertEquals(actual, expected, message);
    }

    public static void assertAll() {
        if (!usedThreadLocal.get()) {
            return;
        }
        
        try {
            softAssertThreadLocal.get().assertAll();
        } catch (AssertionError e) { // الـ SoftAssert يرمي AssertionError عند الفشل وليس Exception عادي
            LogsManager.error("SoftAssert Failure: " + e.getMessage());
            throw e;
        } finally {
            softAssertThreadLocal.remove();
            usedThreadLocal.remove();
        }
    }
}
