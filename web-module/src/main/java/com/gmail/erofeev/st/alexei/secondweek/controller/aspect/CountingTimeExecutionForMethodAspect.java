package com.gmail.erofeev.st.alexei.secondweek.controller.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
@Aspect
public class CountingTimeExecutionForMethodAspect {
    private static final Logger logger = LogManager.getLogger(CountingTimeExecutionForMethodAspect.class);
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Pointcut("execution(public * com.gmail.erofeev.st.alexei.secondweek.controller.impl.DocumentControllerImpl.*(..))")
    public void getNamePointcut() {
    }

    @Around("getNamePointcut()")
    public Object measureMethodPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        TimeCounter timeCounter = new TimeCounter();
        String methodName = joinPoint.getSignature().getName();
        logger.info(methodName + " start in: " + sdf.format(calendar.getTime()));
        timeCounter.start();
        Object proceed = joinPoint.proceed();
        Long executionTime = timeCounter.stop();
        logger.info(methodName + " stop in: " + sdf.format(calendar.getTime()));
        logger.info("method worked: " + executionTime + " ms");
        return proceed;
    }
}
