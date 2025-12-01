package com.api.base;

import com.api.reports.ExtentManager;
import com.api.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		// Initializes the report when the suite starts
		ExtentManager.getInstance();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// Start the test in the report
		ExtentTestManager.createTest(result.getMethod().getMethodName());
		ExtentTestManager.getTest().log(Status.INFO, "Starting Test: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		// Writes everything to the report file
		ExtentManager.getInstance().flush();
	}
}