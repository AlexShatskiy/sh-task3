package com.shatskiy.DAO.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.shatskiy.DAO.NewsDAO;
import com.shatskiy.domain.News;

@Repository("NewsDAOImpl")
public class NewsDAOImpl implements NewsDAO {

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

		return list;
	}

	public News save(News news) {

		Session currentSession = sessionFactory.getCurrentSession();
		news.setStatus(STATUS_ADDED);

		currentSession.saveOrUpdate(news);

		return news;
	}

	public News remove(News news) {

		Session currentSession = sessionFactory.getCurrentSession();
		news.setStatus(STATUS_DELETED);

		currentSession.update(news);

		return news;
	}

	public News fetchById(int newsId) {

		Session currentSession = sessionFactory.getCurrentSession();

		News news = currentSession.get(News.class, newsId);

		return news;
	}
}
