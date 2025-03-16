package com.aivestor.Investor_backend.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class KisConfig {
    public static final String WS_BASE_URL = System.getenv("REST_API_HOST");
}