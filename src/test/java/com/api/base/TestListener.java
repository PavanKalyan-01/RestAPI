package com.api.base;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.reports.ExtentManager;
import com.api.reports.ExtentTestManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.createTest(result.getMethod().getMethodName())
                         .info("Starting Test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail(result.getThrowable());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}
