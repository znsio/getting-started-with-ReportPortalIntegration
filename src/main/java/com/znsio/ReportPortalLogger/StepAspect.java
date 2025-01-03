package com.znsio.ReportPortalLogger;

import com.epam.reportportal.aspect.StepRequestUtils;
import com.epam.reportportal.service.Launch;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.znsio.reportportal.integration.utils.ReportPortalLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import static java.util.Optional.ofNullable;

@Aspect
public class StepAspect {
    private static StepAspect instance;

    private StepAspect() {
        // Private constructor to prevent direct instantiation
    }

    public static StepAspect aspectOf() {
        if (instance == null) {
            instance = new StepAspect();
        }
        return instance;
    }

    @Pointcut("execution(public * *(..)) " +
              "&& !within(com.znsio.ReportPortalLogger.annotations.Hooks) " +
              "&& !within(com.znsio.ReportPortalLogger.TestAspect) " +
              "&& !within(com.znsio.ReportPortalLogger.StepAspect)")
    public void anyMethod() {
    }

    @Before(value = "anyMethod()")
    public void startNestedStep(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        ReportPortalLogger.logInfoMessage("\t\t\t\t->StepAspect: startNestedStep: " + methodName);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        StartTestItemRQ startStepRequest = StepRequestUtils.buildStartStepRequest(signature.getMethod().getDeclaringClass().getSimpleName() + " :: " + signature.getMethod().getName(), signature.getMethod().getDeclaringClass().getName(), signature);
        ofNullable(Launch.currentLaunch()).ifPresent(l -> l.getStepReporter().startNestedStep(startStepRequest));
    }

    @AfterReturning(value = "anyMethod()")
    public void finishNestedStep(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        ReportPortalLogger.logInfoMessage("\t\t\t\t->StepAspect: finishNestedStep: " + methodName);
        ofNullable(Launch.currentLaunch()).ifPresent(l -> l.getStepReporter().finishNestedStep());
    }

    @AfterThrowing(value = "anyMethod()", throwing = "throwable")
    public void failedNestedStep(JoinPoint joinPoint, final Throwable throwable) {
        String methodName = joinPoint.getSignature().getName();
        ReportPortalLogger.logInfoMessage("\t\t\t\t->StepAspect: finishNestedStep: " + methodName);
        ofNullable(Launch.currentLaunch()).ifPresent(l -> l.getStepReporter().finishNestedStep(throwable));
    }
}
