package com.example.SOAP.service;


import com.example.SOAP.entity.Article;
import com.example.SOAP.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public List<Article> getAllArticles() {
		List<Article> list = new ArrayList<>();
		articleRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
//
//	@Override
//	public Article getArticleById(long articleId) {
//		Article obj = articleRepository.findByArticleId(articleId);
//		return obj;
//	}
//
//	@Override
//	public synchronized boolean addArticle(Article article) {
//		 List<Article> list = articleRepository.findByTitleAndCategory(article.getTitle(), article.getCategory());
//         if (list.size() > 0) {
//	           return false;
//         } else {
//	           article = articleRepository.save(article);
//	           return true;
//         }
//	}
//
//	@Override
//	public void updateArticle(Article article) {
//		articleRepository.save(article);
//
//	}
//
//	@Override
//	public void deleteArticle(Article article) {
//		articleRepository.delete(article);
//
//	}

}
