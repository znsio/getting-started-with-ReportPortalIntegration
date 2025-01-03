package com.znsio.ReportPortalLogger;

import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAspect {
    @Pointcut("@annotation(org.testng.annotations.Test)")
    public void testMethodExecution() {
    }

    @Before(value = "testMethodExecution()")
    public void logBeforeTestMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        ReportPortalLogger.logInfoMessage("\t\t\tTestAspect: logBeforeTestMethod: " + methodName);
    }

    @After(value = "testMethodExecution()")
    public void logAfterTestMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        ReportPortalLogger.logInfoMessage("\t\t\tTestAspect: logAfterTestMethod: " + methodName);
    }
}
