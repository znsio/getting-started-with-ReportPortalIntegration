package com.znsio.ReportPortalLogger.package2;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.znsio.ReportPortalLogger.annotations.Hooks;
import com.znsio.ReportPortalLogger.core.MyCore;
import com.znsio.ReportPortalLogger.utils.MyUtils;
import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Sample3_Test  extends Hooks {
    @Test @Attributes(attributes = { @Attribute(key = "myattr", value = "sample1") })
    public void sample3_1() {
        ITestResult result = Reporter.getCurrentTestResult();
        String methodName = result.getMethod().getMethodName();
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + methodName);
        new MyCore().myCoreMethod1();
        new MyUtils().myUtilsMethod2();
    }


    @Test (description = "failingTest")
    @Attributes(attributes = { @Attribute(key = "failingtest", value = "true") })
    public void failingTest() {
        ITestResult result = Reporter.getCurrentTestResult();
        String methodName = result.getMethod().getMethodName();
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + methodName);
        new MyCore().myCoreMethod2();
        throw new RuntimeException("Intentionally failing this test");
    }


    @Test @Attributes(attributes = { @Attribute(key = "myattr", value = "sample2") })
    public void sample3_3() {
        ITestResult result = Reporter.getCurrentTestResult();
        String methodName = result.getMethod().getMethodName();
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + methodName);
    }
}
