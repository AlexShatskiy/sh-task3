package com.shatskiy.service;

import java.util.List;

import com.shatskiy.domain.News;

public interface NewsService 
{
	public List<News> getList();
	public void save(News news);
	public void remove(int[] newsId);
	public News fetchById(int newsId);
}
