package com.localnews.localnews.controllers.newsControllers;

import com.localnews.localnews.services.newsServices.MainNewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainNewsController {

    private final MainNewsService mainNewsService;

    public MainNewsController() {
        this.mainNewsService = new MainNewsService();
    }

    @GetMapping("/main-news")
    public Map<String, String> getMainNews() {
        return mainNewsService.fetchMainNews();
    }
}
