package com.shatskiy.DAO.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.shatskiy.DAO.NewsDAO;
import com.shatskiy.domain.News;

@Repository("NewsDAOImpl")
public class NewsDAOImpl implements NewsDAO {
	
	private static final Logger log = Logger.getLogger(NewsDAOImpl.class);

	private static final String STATUS_ADDED = "added";
	private static final String STATUS_DELETED = "deleted";

	@Autowired
	private SessionFactory sessionFactory;

	public List<News> getList() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<News> theQuery = currentSession.createQuery("from News where status = :paramStatus order by title",
				News.class);
		theQuery.setParameter("paramStatus", STATUS_ADDED);

		List<News> list = theQuery.getResultList();

		log.info("getList()");
		
		return list;
	}

	public void save(News news) {

		Session currentSession = sessionFactory.getCurrentSession();
		news.setStatus(STATUS_ADDED);

		currentSession.saveOrUpdate(news);
		log.info("save(News news)");
	}

	public void remove(News news) {

		Session currentSession = sessionFactory.getCurrentSession();
		news.setStatus(STATUS_DELETED);

		currentSession.update(news);
		log.info("remove(News news)");
	}

	public News fetchById(int newsId) {

		Session currentSession = sessionFactory.getCurrentSession();

		News news = currentSession.get(News.class, newsId);
		log.info("fetchById(int newsId)");

		return news;
	}
}
