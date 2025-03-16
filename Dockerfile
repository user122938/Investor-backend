# 1. 빌드 단계: Gradle 컨테이너에서 애플리케이션 빌드
FROM gradle:8-jdk17 AS builder
WORKDIR /home/gradle/project
COPY build.gradle settings.gradle gradle.* ./
COPY src ./src
RUN gradle clean build -x test

# 2. 실행 단계
FROM openjdk:17-jdk-alpine
WORKDIR /app

ARG JAR_FILE=build/libs/Investor-backend-0.0.1-SNAPSHOT.jar
COPY --from=builder /home/gradle/project/${JAR_FILE} app.jar

# 민감 정보 제거: KIS_APP_KEY, KIS_APP_SECRET, KIS_APP_BASE_URL 등
# 대신, 운영 환경에서는 .env 파일이나 다른 방식으로 주입

ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect

CMD ["java", "-jar", "app.jar"]
EXPOSE 8080
