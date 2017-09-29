package com.shatskiy.service.validator;

import com.shatskiy.domain.News;


public final class NewsValidator {
	
	public static boolean isValid(News news){
		boolean result = false;
		
		if(news != null){
			if(news.getTitle() != null && news.getBrief() != null && news.getContent() != null){
				if(news.getTitle().length() > 1 && news.getBrief().length() > 1 && news.getContent().length() > 1){
					result = true;
				}	
			}
		}
		return result;
	}
}
