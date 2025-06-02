FROM maven:3-eclipse-temurin-22-alpine AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src/ ./src/

RUN mvn clean package -DskipTests

FROM eclipse-temurin:22-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/fastfood-0.0.1-SNAPSHOT.jar ./fastfood.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/fastfood.jar"]