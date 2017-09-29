package com.shatskiy.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shatskiy.domain.News;
import com.shatskiy.service.validator.NewsValidator;

public class TestNewsValidator {
	
	

	@Test
	public void isValidTest() {
		News news = new News();
		news.setTitle("tt");
		news.setBrief("bb");
		news.setContent("cc");
		
		final boolean expected = true;
		final boolean actual = NewsValidator.isValid(news);
		assertEquals(expected, actual);
	}
}
