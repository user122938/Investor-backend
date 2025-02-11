package com.aivestor.Investor_backend.Service;

import com.aivestor.Investor_backend.Domain.NewsDto;
import org.springframework.stereotype.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsCrawlerService {
    public List<NewsDto> getLatestNews() {
        List<NewsDto> newsList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.hankyung.com/economy").get();
            Elements newsItems = doc.select("ul.news-list > li");
            for (Element item : newsItems) {
                // 기사 제목 & URL
                Element titleElement = item.selectFirst("h3.news-tit a");
                String title = titleElement != null ? titleElement.text() : "제목 없음";
                String url = titleElement != null ? titleElement.absUrl("href") : "URL 없음";

                // 기사 요약
                Element summaryElement = item.selectFirst("p.lead");
                String summary = summaryElement != null ? summaryElement.text() : "요약 없음";

                // 기사 날짜
                Element dateElement = item.selectFirst("span.txt-date");
                String date = dateElement != null ? dateElement.text() : "날짜 없음";

                // DTO 객체 생성 및 리스트 추가
                newsList.add(new NewsDto(title, url, summary, date));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
