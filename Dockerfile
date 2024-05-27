FROM openjdk:21-jdk-slim

LABEL authors="qjxlftn@gmail.com"

ENV TZ=Asia/Seoul

WORKDIR /app

COPY . /app

RUN chmod +x ./gradlew

RUN ./gradlew build

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "build/libs/WWDT-0.0.1-SNAPSHOT.jar"]