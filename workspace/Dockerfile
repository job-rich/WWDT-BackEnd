# Build Stage
FROM gradle:8.10.2-jdk AS build
LABEL authors="qjxlftn@gmail.com"

WORKDIR /app

# 루트 프로젝트의 setting.gradle.kts, build.gradle.kts, gradle, gradlew 파일을 복사
COPY settings.gradle.kts build.gradle.kts gradle/ gradlew /app/
# 동작할 모듈과 공유 모듈 설정 파일을 복사
COPY workspace/build.gradle.kts /app/workspace/
COPY shared_kernel/build.gradle.kts /app/shared_kernel/


# Gradle 캐시를 활용하여 종속성 다운로드
RUN ./gradlew build -x test --parallel --continue || true

# 나머지 복사
COPY . /app

# 빌드 [Gradle Daemon 사용하지 않음]
#RUN GRADLE_OPTS="-Xmx1024m -Dorg.gradle.daemon=false" gradle :workspace:clean :workspace:build -x test
# 빌드
RUN gradle :workspace:clean :workspace:build -x test

# Run Stage
FROM openjdk:21-jdk-slim

ENV TZ=Asia/Seoul

WORKDIR /app

# 실행할 jar 파일을 복사
COPY --from=build /app/workspace/build/libs/workspace-0.0.1-SNAPSHOT.jar /app/workspace-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/workspace-0.0.1-SNAPSHOT.jar"]