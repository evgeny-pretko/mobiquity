package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailedTestRepeater implements IRetryAnalyzer {
    private int retryAttempts = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && (retryAttempts < ConfigProvider.RETRY_MAX)) {
            retryAttempts++;
            return true;
        }
        return false;
    }
}
