package com.gmail.erofeev.st.alexei.secondweek.controller.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
@Aspect
public class CountingTimeExecutionForMethodAspect {
    @Autowired
    private TimeCounter timeCounter;
    private static final Logger logger = LogManager.getLogger(CountingTimeExecutionForMethodAspect.class);
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Pointcut("execution(public * com.gmail.erofeev.st.alexei.secondweek.controller.impl.DocumentControllerImpl.*(..))")
    public void getNamePointcut() {
    }

    @Before("getNamePointcut()")
    public void loggingAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info(methodName + " start in: " + sdf.format(calendar.getTime()));
        timeCounter.start();
    }

    @After("getNamePointcut()")
    public void secondAdvice(JoinPoint joinPoint) {
        Long executionTime = timeCounter.stop();
        String methodName = joinPoint.getSignature().getName();
        logger.info(methodName + " stop in: " + sdf.format(calendar.getTime()));
        logger.info("method worked: " + executionTime + " ms");
    }
}
