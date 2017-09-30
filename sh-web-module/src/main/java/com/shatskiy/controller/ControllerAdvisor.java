package com.shatskiy.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.shatskiy.aspect.LoggingAspect;
import com.shatskiy.domain.News;
import com.shatskiy.domain.NewsForm;
import com.shatskiy.service.exception.NewsValidationException;

@ControllerAdvice
public class ControllerAdvisor {
	
	private static final Logger myLog = Logger.getLogger(LoggingAspect.class);

	@ExceptionHandler(NewsValidationException.class)
	public String handleNewsValidationException(Exception exception, Model model) {
		
		NewsForm newsForm = new NewsForm();
		News news = new News();

		newsForm.setNewsMessage(news);
		newsForm.setMessage("validation fail");

		model.addAttribute("newsForm", newsForm);
		
		return "newsEdit-page";
    }
    
	
	@ExceptionHandler(RuntimeException.class)
	public String handleIOException(Exception exception) {
		
		myLog.error(exception);
	
		return "error";
    }
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception exception) {
		
		myLog.error(exception);
	
		return "error";
    }
}
