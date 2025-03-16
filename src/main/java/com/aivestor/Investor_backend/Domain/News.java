package com.aivestor.Investor_backend.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Lob
    private String summary;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Lob
    private String content;

    @Column(nullable = false)
    private String date;
}
