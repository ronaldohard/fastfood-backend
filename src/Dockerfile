FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar br.com.fiap.postech.grupo5.fastfood.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/br.com.fiap.postech.grupo5.fastfood.jar"]