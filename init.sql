CREATE DATABASE IF NOT EXISTS aivestordb;
USE aivestordb;

CREATE TABLE IF NOT EXISTS news (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- PK + Auto Increment
    content TEXT NOT NULL,                 -- 기사 본문
    date VARCHAR(255) NOT NULL,            -- 날짜 (VARCHAR 형식)
    summary TEXT,                           -- 요약
    title VARCHAR(255) NOT NULL,           -- 기사 제목
    url VARCHAR(255) NOT NULL              -- 기사 URL
    );