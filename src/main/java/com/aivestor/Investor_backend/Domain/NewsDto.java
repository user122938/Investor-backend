package com.aivestor.Investor_backend.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private String title;
    private String url;
    private String summary;
    private String date;
}
