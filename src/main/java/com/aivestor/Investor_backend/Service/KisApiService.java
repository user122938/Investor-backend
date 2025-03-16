package com.aivestor.Investor_backend.Service;

import com.aivestor.Investor_backend.config.AccessTokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KisApiService {
    private final WebClient webClient;
    private final AccessTokenManager accessTokenManager;

    @Value("${kis.app.key}")
    private String appKey;

    @Value("${kis.app.secret}")
    private String appSecret;

    @Value("${kis.app.base-url}")
    private String baseUrl;

    public KisApiService(WebClient.Builder webClientBuilder, AccessTokenManager accessTokenManager) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.accessTokenManager = accessTokenManager;
    }

//    public Map<String, Object> getStockPrice(String stockCode) {
//        String token = accessTokenManager.getAccessToken();
//        StockDto dto = new StockDto();
//
//        Stock stock = Stock.builder()
//        //        .queryParam("/uapi/overseas-price/v1/quotations/price")
//                .queryParam("EXCD", "NAS") // ✅ 나스닥 거래소 코드
//                .queryParam("SYMB", stockCode) // ✅ 조회할 종목 코드 (예: AAPL)
//                .build();
//
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/uapi/overseas-price/v1/quotations/price")
//                        .queryParam("EXCD", "NAS") // ✅ 나스닥 거래소 코드
//                        .queryParam("SYMB", stockCode) // ✅ 조회할 종목 코드 (예: AAPL)
//                        .build()
////                .header("authorization", "Bearer " + token) // ✅ Access Token 추가
////                .header("appkey", appKey)
////                .header("appsecret", appSecret)
////                .header("tr_id", "HHDFS00000300") // ✅ 해외 주식 현재가 조회 API 트랜잭션 ID
////                .retrieve()
////                .bodyToMono(Map.class)
////                .block();
//    }
}
