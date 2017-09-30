package test.com.shatskiy.DAO.impl;



import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.shatskiy.DAO.NewsDAO;
import com.shatskiy.domain.News;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextDAOTest.xml")
public class TestNewsDAOImpl {
	
	@Autowired
	@Qualifier("NewsDAOImpl")
	private NewsDAO newsDao;
	
	@Test
	@Transactional
	public void getListConnectionTest(){
		List<News> list = newsDao.getList();

		assertNotNull(list);
	}
	
	@Test
	@Transactional
	public void getListSizeTest(){
		List<News> list = newsDao.getList();

		final int expected = 1;
		final int actual = list.size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	@Transactional
	public void fetchByIdTest() throws ParseException{
		News news = newsDao.fetchById(1);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2000, 00, 01);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");

		final String expectedDate = format.format(cal.getTime());
		final String actualDate = format.format(news.getDate());

		final String expectedTitle = "title";
		final String actualTitle = news.getTitle();
		
		final String expectedBrief = "brief";
		final String actualBrief = news.getBrief();
		
		final String expectedContent = "content";
		final String actualContent = news.getContent();

		assertEquals(actualDate, expectedDate);
		assertEquals(actualTitle, expectedTitle);
		assertEquals(actualBrief, expectedBrief);
		assertEquals(actualContent, expectedContent);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void saveTest(){
		News news = new News();
		news.setTitle("title");
		news.setDate(new Date());
		news.setBrief("brief");
		news.setContent("content");
		newsDao.save(news);
		
		final int newsId = news.getNewsId();
		News savedNews = newsDao.fetchById(newsId);
		
		assertEquals(savedNews.getNewsId(), news.getNewsId());
		assertEquals(savedNews.getTitle(), news.getTitle());
		assertEquals(savedNews.getDate(), news.getDate());
		assertEquals(savedNews.getBrief(), news.getBrief());
		assertEquals(savedNews.getContent(), news.getContent());
		assertEquals(savedNews.getStatus(), news.getStatus());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void removeTest(){
		final int newsId = 1;
		
		News news = newsDao.fetchById(newsId);
		newsDao.remove(news);
		
		News newsTest = newsDao.fetchById(newsId);
		
		assertEquals(newsTest.getStatus(), "deleted");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateTest(){
		final int newsId = 1;
		
		News news = newsDao.fetchById(newsId);
		
		final String expected = "titleTest";
		news.setTitle(expected);
		
		newsDao.save(news);
		news = newsDao.fetchById(newsId);
		
		final String actual = newsDao.fetchById(newsId).getTitle();
		
		assertEquals(expected, actual);
	}
}
