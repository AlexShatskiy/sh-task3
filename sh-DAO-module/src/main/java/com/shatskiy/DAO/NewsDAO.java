package com.shatskiy.DAO;

import java.util.List;
import com.shatskiy.domain.News;

public interface NewsDAO 
{
	public List<News> getList();
	public void save(News news);
	public void remove(News news);
	public News fetchById(int newsId);
}
