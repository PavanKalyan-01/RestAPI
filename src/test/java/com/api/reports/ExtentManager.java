package com.api.reports;

import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestResult;

import com.api.base.TestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends TestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;

    private String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        repName = "API_Automation_Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/extent-reports/" + repName);
        sparkReporter.config().setDocumentTitle("Rest Assured API Automation Report");
        sparkReporter.config().setReportName("API Test Results");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Pet Store API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Pavan Kalyan");
    }

    public void onTestStart(ITestResult result) {
        // Create a new test instance for each test method
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            test.log(Status.SKIP, result.getThrowable());
        }
    }

    public void onFinish(ITestContext testContext) {
        if (extent != null) {
            extent.flush();
        }
    }
}