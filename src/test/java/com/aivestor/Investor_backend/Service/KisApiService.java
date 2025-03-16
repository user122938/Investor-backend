package com.aivestor.Investor_backend.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KisApiService {

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Value("${kis.app.key}")
    private String appKey;

    @Value("${kis.app.secret}")
    private String appSecret;

    @Value("${kis.app.base-url}")
    private String baseUrl;

    private String accessToken;


    public void initWebClient() {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }
//    public Map<String, Object> getNasdaqStockPrice(String stockCode) {
//       String token = getAccessToken();
//
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/uapi/overseas-price/v1/quotations/price")
//                        .queryParam("EXCD", "NAS")  // 📌 나스닥 시장 코드
//                        .queryParam("SYMB", stockCode)  // 📌 조회할 종목 코드 (예: AAPL)
//                        .build()
//                )
//                .header("authorization", "Bearer " + token)
//                .header("appkey", appKey)
//                .header("appsecret", appSecret)
//                .header("tr_id", "HHDFS00000300") // 📌 해외 주식 현재가 조회
//                .retrieve()
//                .bodyToMono(Map.class)
//                .block();
//    }
}
