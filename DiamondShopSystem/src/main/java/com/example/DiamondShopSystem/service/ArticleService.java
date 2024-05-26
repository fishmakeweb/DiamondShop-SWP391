package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Article;
import com.example.DiamondShopSystem.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public Article findArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElse(null);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article newArticle) {
        return articleRepository.findById(id)
                .map(existingArticle -> {
                    existingArticle.setTitle(newArticle.getTitle());
                    existingArticle.setContent(newArticle.getContent());
                    return articleRepository.save(existingArticle);
                }).orElseGet(() -> {
                    newArticle.setArticleId(id);
                    return articleRepository.save(newArticle);
                });
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
