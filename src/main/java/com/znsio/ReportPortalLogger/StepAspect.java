package com.znsio.ReportPortalLogger;

import com.epam.reportportal.aspect.StepRequestUtils;
import com.epam.reportportal.service.Launch;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import static java.util.Optional.ofNullable;

@Aspect
public class StepAspect {
    @Pointcut("execution(public * *(..)) && !within(com.znsio.ReportPortalLogger.annotations.Hooks)")
    public void anyMethod() {
    }

    @Before(value = "anyMethod()")
    public void startNestedStep(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        StartTestItemRQ startStepRequest = StepRequestUtils.buildStartStepRequest(signature.getMethod().getDeclaringClass().getSimpleName() + " :: " + signature.getMethod().getName(), signature.getMethod().getDeclaringClass().getName(), signature);
        ofNullable(Launch.currentLaunch()).ifPresent(l -> l.getStepReporter().startNestedStep(startStepRequest));
    }

    @AfterReturning(value = "anyMethod()")
    public void finishNestedStep() {
        ofNullable(Launch.currentLaunch()).ifPresent(l -> l.getStepReporter().finishNestedStep());
    }

    @AfterThrowing(value = "anyMethod()", throwing = "throwable")
    public void failedNestedStep(final Throwable throwable) {
        ofNullable(Launch.currentLaunch()).ifPresent(l -> l.getStepReporter().finishNestedStep(throwable));
    }
}
