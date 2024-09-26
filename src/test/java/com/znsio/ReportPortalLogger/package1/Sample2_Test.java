package com.znsio.ReportPortalLogger.package1;

import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import com.znsio.ReportPortalLogger.annotations.Hooks;
import com.znsio.ReportPortalLogger.core.MyCore;
import com.znsio.ReportPortalLogger.utils.MyUtils;
import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Sample2_Test  extends Hooks {
    @Test (description = "my sample2_1 test")
    @Attributes(multiValueAttributes = {@MultiValueAttribute(isNullKey = true, values = {"v1", "v2" }) })
    public void sample2_1() {
        ITestResult result = Reporter.getCurrentTestResult();
        String methodName = result.getMethod().getMethodName();
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + methodName);
    }

    @Test (description = "my sample2_2 test")
    @Attributes(multiValueAttributes = {@MultiValueAttribute(isNullKey = false, key = "components", values = {"v1", "v2" }) })
    public void sample2_2() {
        ITestResult result = Reporter.getCurrentTestResult();
        String methodName = result.getMethod().getMethodName();
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + methodName);
        new MyCore().myCoreMethod1();
        new MyUtils().myUtilsMethod2();
    }
}
