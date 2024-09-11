package com.znsio.ReportPortalLogger.utils;

import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.testng.ITestResult;
import org.testng.Reporter;

public class MyUtils {
    public void myUtilsMethod1() {
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + getMethodName());
        myUtilsMethod2();
        myUtilsMethod3();
    }

    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public void myUtilsMethod2() {
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + getMethodName());
        myUtilsMethod3();
    }

    private void myUtilsMethod3() {
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + getMethodName());
    }
}
