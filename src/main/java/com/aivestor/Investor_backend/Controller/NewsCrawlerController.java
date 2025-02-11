package com.aivestor.Investor_backend.Controller;

import com.aivestor.Investor_backend.Domain.NewsDto;
import com.aivestor.Investor_backend.Service.NewsCrawlerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/news")
@AllArgsConstructor
public class NewsCrawlerController {
    private final NewsCrawlerService newsCrawlerService;

    @GetMapping("/latest")
    public List<NewsDto> getLatestNews() {
        return newsCrawlerService.getLatestNews();
    }
}
