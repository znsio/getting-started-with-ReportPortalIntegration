package com.znsio.ReportPortalLogger.annotations;

import org.testng.annotations.BeforeSuite;

public class Hooks {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }
}
