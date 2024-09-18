package com.znsio.ReportPortalLogger.annotations;

import com.znsio.reportportal.integration.listener.ReportPortalListener;
import org.testng.annotations.BeforeSuite;

public class Hooks {

    public Hooks() {
        ReportPortalListener.getInstance();
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }
}
