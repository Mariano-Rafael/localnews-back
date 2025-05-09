package com.localnews.localnews.services.newsServices;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.newsModels.NewsApiResponse;
import com.localnews.localnews.models.newsModels.NewsModel;
import com.localnews.localnews.repositories.newsRepositories.NewsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@PropertySource("classpath:/application-secrets.properties")
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Value("${newsApi.key}")
    private String newsApiKey;

    public Page<NewsModel> searchNews(String title, String content, Pageable pageable) {
        if (title == null && content == null)  {
            return newsRepository.findAllNews(pageable);
    } else {
        return newsRepository.findByTitleOrContentIgnoreCaseContaining(title, content, pageable);
    }
}

    public void saveNews(NewsModel newsModel) {
        newsRepository.save(newsModel);
    }


    public List<NewsApiResponse.Article> fetchNewsFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", newsApiKey);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String url = "https://newsapi.org/v2/top-headlines?sources=google-news-br&&apikey=" + newsApiKey;

        ResponseEntity<NewsApiResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, NewsApiResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return Objects.requireNonNull(response.getBody()).getArticles();
        }
        else {
            throw new RuntimeException("Erro ao buscar notícias");
        }
    }

    @PostConstruct
    public void fetchAndSaveNews() {
        try {
            List<NewsApiResponse.Article> articles = fetchNewsFromApi();
            for (NewsApiResponse.Article article : articles) {
                NewsModel newsModel = new NewsModel();
                newsModel.setTitle(article.getTitle());
                newsModel.setContent(article.getDescription());
                newsModel.setAuthor(article.getAuthor());
                newsModel.setPublicationDate(LocalDateTime.parse(article.getPublishedAt(), DateTimeFormatter.ISO_DATE_TIME));
                newsModel.setUrlNews(article.getUrl());

                saveNews(newsModel);
            }
        }
        catch (NullPointerException e) {
            new BooleanResponseModel(false, e.getMessage());
        }
    }
}
