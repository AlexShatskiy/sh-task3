package com.shatskiy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	
	@RequestMapping("/")
	public String initNews() {
		
		return "redirect:news/list";	
	}
}
