FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -P postgresdb

FROM openjdk:17-alpine AS runner

WORKDIR /app

COPY --from=builder ./app/target/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
