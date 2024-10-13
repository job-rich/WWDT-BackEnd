# Build Stage
FROM gradle:8.10.2-jdk AS build
LABEL authors="qjxlftn@gmail.com"

WORKDIR /app

COPY . /app

RUN gradle clean build -x test

# Run Stage
FROM openjdk:21-jdk-slim

ENV TZ=Asia/Seoul

WORKDIR /app

COPY --from=build /app/build/libs/WWDT-0.0.1-SNAPSHOT.jar /app/WWDT-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/WWDT-0.0.1-SNAPSHOT.jar"]