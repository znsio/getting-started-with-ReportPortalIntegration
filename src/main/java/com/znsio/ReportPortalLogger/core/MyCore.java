package com.znsio.ReportPortalLogger.core;

import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.testng.ITestResult;
import org.testng.Reporter;

public class MyCore {
    public void myCoreMethod1() {
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + getMethodName());
        myCoreMethod2();
        myCoreMethod3();
    }

    public void myCoreMethod2() {
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + getMethodName());
        myCoreMethod3();
    }

    private void myCoreMethod3() {
        ReportPortalLogger.logInfoMessage(this.getClass().getSimpleName() + " - " + getMethodName());
    }


    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
