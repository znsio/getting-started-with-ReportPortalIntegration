package com.znsio.ReportPortalLogger;

import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAspect {
    private static TestAspect instance;

    private TestAspect() {
        // Private constructor to prevent direct instantiation
    }

    public static TestAspect aspectOf() {
        if (instance == null) {
            instance = new TestAspect();
        }
        return instance;
    }

    @Pointcut("@annotation(org.testng.annotations.Test)")
    public void testMethodExecution() {
    }

    @Before(value = "testMethodExecution()")
    public void logBeforeTestMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        ReportPortalLogger.logInfoMessage("\t\t\t\t->TestAspect: logBeforeTestMethod: " + methodName);
    }

    @After(value = "testMethodExecution()")
    public void logAfterTestMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        ReportPortalLogger.logInfoMessage("\t\t\t\t->TestAspect: logAfterTestMethod: " + methodName);
    }
}
