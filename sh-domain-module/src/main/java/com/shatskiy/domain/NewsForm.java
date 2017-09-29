package com.shatskiy.domain;

import java.util.List;

import javax.validation.Valid;

public class NewsForm {
	
	@Valid
	private News newsMessage;
	private List<News> newsList;
	private String message;
	
	public NewsForm() {
		super();
	}

	public News getNewsMessage() {
		return newsMessage;
	}

	public void setNewsMessage(News newsMessage) {
		this.newsMessage = newsMessage;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((newsList == null) ? 0 : newsList.hashCode());
		result = prime * result + ((newsMessage == null) ? 0 : newsMessage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsForm other = (NewsForm) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (newsList == null) {
			if (other.newsList != null)
				return false;
		} else if (!newsList.equals(other.newsList))
			return false;
		if (newsMessage == null) {
			if (other.newsMessage != null)
				return false;
		} else if (!newsMessage.equals(other.newsMessage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewsForm [newsMessage=" + newsMessage + ", newsList=" + newsList + ", message=" + message + "]";
	}	
}
