package com.aivestor.Investor_backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class AuthTokenResponse {
    private String accessToken;
    private String tokenType;
    private long expiresIn;  // ✅ `expires_in`은 Number 타입이므로 long 사용 가능
    private String accessTokenExpired; // ✅ `access_token_token_expired`는 String으로 받음

    // ✅ `access_token_token_expired`를 Unix Timestamp (밀리초)로 변환하는 메서드
    public long getAccessTokenExpiredAsLong() {
        if (accessTokenExpired == null || accessTokenExpired.isEmpty()) {
            return System.currentTimeMillis() + (expiresIn * 1000); // 기본 24시간 후
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(accessTokenExpired, formatter);
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
