package com.znsio.ReportPortalLogger.package2;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import com.znsio.ReportPortalLogger.annotations.Hooks;
import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Sample4_Test  extends Hooks {
    @Test (description = "my Sample4_Test_sample1 test")
    @Attributes(attributes = { @Attribute(key = "myattr", value = "sample2") }, multiValueAttributes = {@MultiValueAttribute(isNullKey = false, key = "components", values = {"v1", "v2", "v3" }) })
    public void sample1() {
        ITestResult result = Reporter.getCurrentTestResult();
        String methodName = result.getMethod().getMethodName();
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + methodName);
    }

    @Test (description = "my Sample4_Test_sample2 test")
    @Attributes(multiValueAttributes = {@MultiValueAttribute(isNullKey = false, key = "components", values = {"v1", "v2", "v3" }) })
    public void sample2() {

        ITestResult result = Reporter.getCurrentTestResult();
        String methodName = result.getMethod().getMethodName();
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + methodName);
    }
}
