package com.vadim.budgettracker.logging;

import com.vadim.budgettracker.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Slf4j
public class UserAspect {

    private final Logger logger;

    public UserAspect() {
        logger = LogManager.getLogger(UserController.class);
    }

    @Pointcut("execution(* com.vadim.budgettracker.controller.UserController.getUser*(..))")
    private void getUser() {

    }

    @Pointcut("execution(* com.vadim.budgettracker.controller.UserController.deleteUser())")
    private void deleteUser() {

    }

    @Around("getUser()")
    public Object loggingGetUser(ProceedingJoinPoint point) {
        System.out.println("1");
        String mArgs = Arrays.toString(point.getArgs());
        String mName = point.getSignature().getName();
        Object mObject = null;
        try {
            mObject = point.proceed();
            log.info("Logging method: " + mName + " its args: " + mArgs + " its returned value " + mObject);
            return mObject;
        } catch (Throwable e) {
            log.error("There was an exception");
            log.error("Exception message: " + e.getMessage());
            log.error("Exception name: " + e.getClass().getName());
        }
        return mObject;
    }

    @Around("deleteUser()")
    public Object loggingDeleteUser(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
       // Method method = signature.getMEthod;
        return null;
    }
}
