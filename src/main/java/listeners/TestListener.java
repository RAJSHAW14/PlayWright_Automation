package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.microsoft.playwright.Page;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ScreenshotUtils;

import java.lang.reflect.Field;
import java.util.Objects;

public class TestListener implements ITestListener {
    private static ExtentReports extentReports;
    private static ExtentTest test;
    @Override
    public void onStart(ITestContext context){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tester","Abhishek");
        extentReports.setSystemInfo("Framwork","Playwrite with JAVA");
    }

    @Override
    public void onTestStart(ITestResult result){
        test = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        Object currentClass = result.getInstance();
        try{
            Field pageField = currentClass.getClass().getSuperclass().getDeclaredField("page");
            pageField.setAccessible(true);
            Page page = (Page) pageField.get(currentClass);
            String screenshotPath = ScreenshotUtils.captureScreenshot(page, result.getMethod().getMethodName());
            test.fail("❌ Test Failed: "+ result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }catch (Exception e){
            test.fail("❌ Test Failed but screenshot could not be capture " + e.getMessage());
            test.fail(e);
        }

    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.pass("✅ Test Passed");
    }
    @Override
    public void onTestSkipped(ITestResult result){
        test.skip(" Test Skipped: "+result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context){
        extentReports.flush();
    }

}
