package com.znsio.ReportPortalLogger.annotations;

import com.znsio.reportportal.integration.listener.ReportPortalListener;
import com.znsio.reportportal.integration.listener.SkipOnFailureListener;
import org.testng.annotations.Listeners;

@Listeners({ReportPortalListener.class, SkipOnFailureListener.class})
public class Hooks {
}
