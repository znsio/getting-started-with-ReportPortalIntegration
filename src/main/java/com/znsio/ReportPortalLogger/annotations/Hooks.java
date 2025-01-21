package com.znsio.ReportPortalLogger.annotations;

import com.znsio.reportportal.integration.listener.ReportPortalListener;
import com.znsio.reportportal.integration.listener.SkipOnFailureListener;
import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.testng.annotations.*;
import org.testng.reporters.EmailableReporter2;
import org.testng.reporters.TestHTMLReporter;

@Listeners({ReportPortalListener.class, SkipOnFailureListener.class, EmailableReporter2.class, TestHTMLReporter.class})
public class Hooks {
    @BeforeSuite
    public void beforeSuite() {
        ReportPortalLogger.logInfoMessage("<- Before Suite");
    }

    @BeforeTest
    public void beforeTest() {
        ReportPortalLogger.logInfoMessage("\t<- Before Test");
    }

    @BeforeClass
    public void beforeClass() {
        ReportPortalLogger.logInfoMessage("\t\t<- Before Class");
    }

    @BeforeMethod
    public void beforeMethod() {
        ReportPortalLogger.logInfoMessage("\t\t\t<- Before Method");
    }

    @AfterMethod
    public void afterMethod() {
        ReportPortalLogger.logInfoMessage("\t\t\t-> After Method");
    }

    @AfterClass
    public void afterClass() {
        ReportPortalLogger.logInfoMessage("\t\t->After Class");
    }

    @AfterTest
    public void afterTest() {
        ReportPortalLogger.logInfoMessage("\t->After Test");
    }

    @AfterSuite
    public void afterSuite() {
        ReportPortalLogger.logInfoMessage("After Suite");
    }
}
