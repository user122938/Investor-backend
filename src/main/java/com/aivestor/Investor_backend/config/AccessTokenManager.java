package com.aivestor.Investor_backend.config;

import com.aivestor.Investor_backend.dto.response.AuthTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class AccessTokenManager {
    private final WebClient webClient;

    @Value("${kis.app.key}")
    private String appKey;

    @Value("${kis.app.secret}")
    private String appSecret;

    @Value("${kis.app.base-url}")
    private String baseUrl;

    // ✅ API URL을 static final로 관리
    private static final String TOKEN_API_PATH = "/oauth2/tokenP";
    private volatile String accessToken;
    private volatile long tokenExpiryTime = 0; // ✅ 토큰 만료 시간 (밀리초)

    public AccessTokenManager(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // ✅ 기존 Access Token이 유효하면 반환, 만료되었으면 갱신
    public String getAccessToken() {
        long currentTime = System.currentTimeMillis();

        // ✅ 토큰이 존재하고, 만료되지 않았다면 재사용
        if (accessToken != null && currentTime < tokenExpiryTime) {
            return accessToken;
        }

        // ✅ 만료되었거나 토큰이 없으면 새로 발급
        return generateAccessToken();
    }

    // ✅ Access Token 갱신 (24시간마다 갱신하도록 변경)
    public String generateAccessToken() {
        AuthTokenResponse tokenResponse = requestNewAccessToken();
        if (tokenResponse == null || tokenResponse.getAccessToken() == null) {
            throw new RuntimeException("액세스 토큰을 가져올 수 없습니다.");
        }

        // ✅ 새로운 토큰 저장
        accessToken = tokenResponse.getAccessToken();

        // ✅ 만료 시간 계산 (access_token_token_expired가 있으면 우선 사용)
        if (tokenResponse.getAccessTokenExpired() != null) {
            tokenExpiryTime = tokenResponse.getAccessTokenExpiredAsLong();
        } else {
            tokenExpiryTime = System.currentTimeMillis() + (tokenResponse.getExpiresIn() * 1000);
        }

        return accessToken;
    }


    // ✅ 실제 Access Token을 요청하는 메서드
    private AuthTokenResponse requestNewAccessToken() {
        Map<String, String> requestBody = Map.of(
                "grant_type", "client_credentials",
                "appkey", appKey,
                "appsecret", appSecret
        );

        // ✅ .uri(baseUrl + TOKEN_API_PATH) 사용하여 URL 직접 전달
        Mono<AuthTokenResponse> mono = webClient.post()
                .uri(baseUrl + TOKEN_API_PATH)
                .header("content-type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(AuthTokenResponse.class);

        return mono.block();
    }
}
