package com.shatskiy.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger myLog = Logger.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.shatskiy.controller.*.*(..))")
	private void forControllerPackage(){}
	
	@Before("forControllerPackage()")
	public void logger(JoinPoint joinPoint) {
		String theMethod = joinPoint.getSignature().toShortString();
		myLog.info(theMethod);
	}
}
