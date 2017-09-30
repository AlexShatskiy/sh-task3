package com.shatskiy.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shatskiy.DAO.NewsDAO;
import com.shatskiy.domain.News;
import com.shatskiy.service.NewsService;
import com.shatskiy.service.exception.NewsValidationException;
import com.shatskiy.service.validator.NewsValidator;

@Service
public class NewsServiceImpl implements NewsService {

	private static final Logger log = Logger.getLogger(NewsServiceImpl.class);

	@Autowired
	@Qualifier("NewsDAOImpl")
	private NewsDAO newsDAO;

	@Transactional
	public List<News> getList() {
		log.info("getList()");
		
		return newsDAO.getList();
	}

	@Transactional
	public void save(News news) {

		if (NewsValidator.isValid(news)) {
			if (news.getDate() == null) {
				news.setDate(new Date());
			}	
			
			newsDAO.save(news);

		} else {
			log.error("fail in save()");
			throw new NewsValidationException("fail in save");
		}
	}

	@Transactional
	public void remove(int[] newsId) {

		if (newsId == null) {
			log.info("newsId == null");
			} else {
			for (int id : newsId) {
				News news = newsDAO.fetchById(id);
				newsDAO.remove(news);
			}
		}
	}

	@Transactional
	public News fetchById(int newsId) {

		if (newsId < 0) {
			log.error("fail in fetchById()");
			throw new NewsValidationException("fail in fetchById");
		}
		News news = newsDAO.fetchById(newsId);

		return news;
	}
}