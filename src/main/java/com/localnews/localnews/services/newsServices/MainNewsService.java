package com.localnews.localnews.services.newsServices;

import com.localnews.localnews.models.BooleanResponseModel;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainNewsService {

    public Map<String, String> fetchMainNews() {
        Map<String, String> response = new HashMap<>();

        try {
            URI uri = new URI("https://g1.globo.com/rss/g1/");
            URL url = uri.toURL();
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));

            SyndEntry mainNews = feed.getEntries().getFirst();

            response.put("title", mainNews.getTitle());
            response.put("link", mainNews.getLink());

            String imageUrl = getImageUrlFromDescription(mainNews.getDescription().getValue());
            if (imageUrl != null) {
                response.put("image", imageUrl);
            }

        } catch (NullPointerException | URISyntaxException | MalformedURLException e) {
            new BooleanResponseModel(false, e.getMessage());
        } catch (FeedException | IOException e) {
            throw new RuntimeException(String.valueOf(new BooleanResponseModel(false,
                    e.getMessage())));
        }

        return response;
    }

    private String getImageUrlFromDescription(String description) {

        Pattern pattern = Pattern.compile("<img src=\"(https[^\"]+)\"");
        Matcher matcher = pattern.matcher(description);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
