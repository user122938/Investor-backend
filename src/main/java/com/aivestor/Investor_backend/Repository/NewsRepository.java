package com.aivestor.Investor_backend.Repository;

import com.aivestor.Investor_backend.Domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    // 중복 저장 방지를 위해 URL을 기준으로 기존 데이터가 있는지 확인하는 메서드 (선택사항)
    Optional<News> findByUrl(String url);
}
