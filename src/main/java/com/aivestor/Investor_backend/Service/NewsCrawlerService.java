package com.aivestor.Investor_backend.Service;

import com.aivestor.Investor_backend.Domain.News;
import com.aivestor.Investor_backend.Domain.NewsDto;
import com.aivestor.Investor_backend.Repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsCrawlerService {
    private final NewsRepository newsRepository;

    public List<NewsDto> getLatestNews() {
        List<NewsDto> newsList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.hankyung.com/economy/economic-policy").get();
            Elements newsItems = doc.select("ul.news-list > li");
            for (Element item : newsItems) {
                // 기사 제목 & URL
                Element titleElement = item.selectFirst("h2.news-tit a");
                String title = titleElement != null ? titleElement.text() : "제목 없음";
                String url = titleElement != null ? titleElement.absUrl("href") : "URL 없음";

                // 기사 요약
                Element summaryElement = item.selectFirst("p.lead");
                String summary = summaryElement != null ? summaryElement.text() : "요약 없음";

                // 기사 날짜
                Element dateElement = item.selectFirst("p.txt-date");
                String date = dateElement != null ? dateElement.text() : "날짜 없음";

                NewsDto dto = new NewsDto(title, url, summary, date);
                newsList.add(dto);

                if (!newsRepository.findByUrl(url).isPresent()) {
                    // News 엔티티로 변환 (content 필드는 현재 DTO에 없으므로 빈 문자열이나 다른 값으로 처리)
                    News news = News.builder()
                            .title(title)
                            .url(url)
                            .summary(summary)
                            .date(date)
                            .content("") // 실제 뉴스 내용을 별도로 크롤링해서 넣거나, 나중에 업데이트할 수 있습니다.
                            .build();

                    newsRepository.save(news);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
