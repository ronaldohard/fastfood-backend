FROM eclipse-temurin:22-jdk-alpine

WORKDIR /app

COPY ../target/fastfood-0.0.1-SNAPSHOT.jar fastfood.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/fastfood.jar"]