# Build Stage
FROM gradle:8.10.2-jdk AS build
LABEL authors="qjxlftn@gmail.com"

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle.kts /app/
COPY settings.gradle.kts /app/

# Gradle 캐시를 활용하여 종속성 다운로드
RUN ./gradlew build -x test --parallel --continue || true

# 나머지 복사
COPY . /app

# 빌드
RUN gradle clean build -x test

# Run Stage
FROM openjdk:21-jdk-slim

ENV TZ=Asia/Seoul

WORKDIR /app

# 실행할 jar 파일을 복사
COPY --from=build /app/auth/build/libs/auth-0.0.1-SNAPSHOT.jar /app/auth-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/auth-0.0.1-SNAPSHOT.jar"]