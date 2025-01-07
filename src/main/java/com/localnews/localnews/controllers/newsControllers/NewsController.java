package com.localnews.localnews.controllers.newsControllers;

import com.localnews.localnews.models.newsModels.NewsModel;
import com.localnews.localnews.services.newsServices.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<Page<NewsModel>> getAllNews(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            Pageable pageable) {

        Page<NewsModel> newsPage = newsService.searchNews(title, content, pageable);
        return ResponseEntity.ok(newsPage);
    }
}
