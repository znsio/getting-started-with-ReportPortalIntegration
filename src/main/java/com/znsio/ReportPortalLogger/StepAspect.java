package com.znsio.ReportPortalLogger;/*
 * Copyright 2019 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
