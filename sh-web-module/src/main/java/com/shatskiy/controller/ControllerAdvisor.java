package com.shatskiy.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shatskiy.aspect.LoggingAspect;

@ControllerAdvice
public class ControllerAdvisor {
	
	private static final Logger myLog = Logger.getLogger(LoggingAspect.class);

	@ExceptionHandler(RuntimeException.class)
	public String handleIOException(Exception exception) {
		
		myLog.error(exception);
	
		return "redirect:list";
    }
}
