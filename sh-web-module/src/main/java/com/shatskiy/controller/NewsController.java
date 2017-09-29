package com.shatskiy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.shatskiy.domain.News;
import com.shatskiy.domain.NewsForm;
import com.shatskiy.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@GetMapping("/list")
	public String listNews(Model model) {

		List<News> list = newsService.getList();

		NewsForm newsForm = new NewsForm();
		newsForm.setNewsList(list);

		model.addAttribute("newsForm", newsForm);

		return "newsList-page";
	}

	@PostMapping("/delete")
	public String deleteNews(@RequestParam(value = "newsId", required=false) int[] newsId) {
		newsService.remove(newsId);
		
		return "redirect:list";
	}
	


	@GetMapping("/add")
	public String addNews(Model model) {
		
		NewsForm newsForm = new NewsForm();
		News news = new News();

		newsForm.setNewsMessage(news);

		model.addAttribute("newsForm", newsForm);
		
		return "newsEdit-page";
	}

	@PostMapping("/save")
	public String saveNews(@Valid @ModelAttribute("newsForm") NewsForm newsForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "newsEdit-page";
		}
		News news = newsForm.getNewsMessage();

		newsService.save(news);
		return "redirect:list";
	}

	@GetMapping("/view")
	public String viewNews(@RequestParam("newsId") int newsId, Model model) {

		News news = newsService.fetchById(newsId);

		NewsForm newsForm = new NewsForm();
		newsForm.setNewsMessage(news);

		model.addAttribute("newsForm", newsForm);

		return "newsView-page";
	}

	@GetMapping("/edit")
	public String editNews(@RequestParam("newsId") int newsId, Model model) {

		News news = newsService.fetchById(newsId);

		NewsForm newsForm = new NewsForm();
		newsForm.setNewsMessage(news);

		model.addAttribute("newsForm", newsForm);

		return "newsEdit-page";
	}
}
