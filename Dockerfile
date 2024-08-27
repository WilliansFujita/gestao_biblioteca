FROM gradle:8.3.0-jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle ./gradle

RUN gradle build -x test --parallel --continue --stacktrace || true

COPY src ./src

RUN gradle clean build -x test --parallel --continue --stacktrace

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]