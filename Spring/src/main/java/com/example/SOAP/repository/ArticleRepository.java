package com.example.SOAP.repository;


import com.example.SOAP.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	Article findByArticleId(long articleId);
    List<Article> findByTitleAndCategory(String title, String category);
}
