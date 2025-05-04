FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package
FROM openjdk:21-jdk AS runner

WORKDIR /app
COPY --from=builder ./app/target/*.jar ./app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
